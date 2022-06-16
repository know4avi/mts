package com.cg.mts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.exception.AdmissionComMemNotFoundException;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.repository.IAdmissionCommiteeMemberRepository;

@Service
public class AdmissionCommiteeMemberService implements IAdmissionCommiteeMemberService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IAdmissionCommiteeMemberRepository admissionCommiteeMemberRepository;
	
	@Override
	public AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember acm) {
		LOG.info("Adding admissionCommiteeMember");
		return admissionCommiteeMemberRepository.save(acm);
	}

	@Override
	public AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember acm) {
		LOG.info(acm.toString());
		Optional<AdmissionCommiteeMember> admissionComOptional = admissionCommiteeMemberRepository.findById(acm.getAdminId());

		if (admissionComOptional.isPresent()) {
			return admissionCommiteeMemberRepository.save(acm);
		} else {
			String exceptionMessage = "AdmissionCommiteeMember with adminId " + acm.getAdminId() + " does not exist.";
			LOG.warn(exceptionMessage);
			throw new AdmissionComMemNotFoundException(exceptionMessage);
		}
	}

	@Override
	public AdmissionCommiteeMember viewCommiteeMember(int adminId) {
		LOG.info("viewCommiteeMember " + adminId);
		Optional<AdmissionCommiteeMember> acmOptional = admissionCommiteeMemberRepository.findById(adminId);
		if (acmOptional.isPresent()) {
			return acmOptional.get();
		} else {
			String exceptionMessage = "CommiteeMember with adminId " + adminId + " does not exist.";
			LOG.warn(exceptionMessage);
			throw new CourseNotFoundException(exceptionMessage);
		}
	}

	@Override
	public AdmissionCommiteeMember removeCommiteeMember(int adminId) {
		LOG.info("Removing CommiteeMember");
		Optional<AdmissionCommiteeMember> acmOptional = admissionCommiteeMemberRepository.findById(adminId);
		if (acmOptional.isPresent()) {
			AdmissionCommiteeMember acm = acmOptional.get();
			admissionCommiteeMemberRepository.delete(acm);
			return acm;
		} else {
			throw new AdmissionComMemNotFoundException("CommiteeMember with adminId " + adminId + " not found");
		}
		
	}

	@Override
	public List<AdmissionCommiteeMember> viewAllCommiteeMember() {
		LOG.info("Getting all the commitee members");
		List<AdmissionCommiteeMember> acmList = admissionCommiteeMemberRepository.findAll();
		return acmList;
	}

}
