package com.cg.mts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.AdmissionStatus;
import com.cg.mts.entities.Applicant;
import com.cg.mts.exception.ApplicantNotFoundException;
import com.cg.mts.repository.IApplicantRepository;

@Service
public class ApplicantService implements IApplicantService{
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IApplicantRepository applicantRepository;
	
	@Override
	public Applicant addApplicant(Applicant applicant) {
		return applicantRepository.save(applicant);
	}

	@Override
	public Applicant updateApplicant(Applicant applicant) {
		LOG.info(applicant.toString());
		Optional<Applicant> applicantOptional = applicantRepository.findById(applicant.getApplicantId());
		
		if (applicantOptional.isPresent()) {
			return applicantRepository.save(applicant);
		}
		else {
			String exceptionMessage = "Applicant with applicantId " + applicant.getApplicantId() + " does not exist.";
			LOG.warn(exceptionMessage);
			throw new ApplicantNotFoundException(exceptionMessage);
		}
	}

	@Override
	public Applicant deleteApplicant(int applicantId) {
		LOG.info("Applicant removed");
		Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
		if (applicantOptional.isPresent()) {
			Applicant app =applicantOptional.get();
			applicantRepository.delete(app);
			return app;
		} else {
			throw new ApplicantNotFoundException("Applicant with applicantId " + applicantId + " not found");
		}
	}

	@Override
	public Applicant viewApplicant(int applicantId) {
		LOG.info("getApplicantById " + applicantId);
		Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
		if (applicantOptional.isPresent()) {
			return applicantOptional.get();
		} else {
			String exceptionMessage = "Applicant with applicantId " + applicantId + " does not exist.";
			LOG.warn(exceptionMessage);
			throw new ApplicantNotFoundException(exceptionMessage);
		}
	}

	@Override
	public List<Applicant> viewAllApplcants() {
		LOG.info("Getting all the applicants");
		List<Applicant> applicantList = applicantRepository.findAll();
		return applicantList;
	}

}
