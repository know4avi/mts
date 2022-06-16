package com.cg.mts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.entities.Role;
import com.cg.mts.exception.AdmissionComMemNotFoundException;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.exception.NotAuthorizedException;
import com.cg.mts.repository.IAdmissionCommiteeMemberRepository;

@Service
public class AdmissionCommiteeMemberService implements IAdmissionCommiteeMemberService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IAdmissionCommiteeMemberRepository admissionCommiteeMemberRepository;

	@Autowired
	private MtsUserService appUserService;

	@Override
	public AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember acm) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Adding admissionCommiteeMember");
				return admissionCommiteeMemberRepository.save(acm);

			} else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}

	@Override
	public AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember acm) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info(acm.toString());
				Optional<AdmissionCommiteeMember> admissionComOptional = admissionCommiteeMemberRepository
						.findById(acm.getAdminId());

				if (admissionComOptional.isPresent()) {
					return admissionCommiteeMemberRepository.save(acm);
				} else {
					String exceptionMessage = "AdmissionCommiteeMember with adminId " + acm.getAdminId()
							+ " does not exist.";
					LOG.warn(exceptionMessage);
					throw new AdmissionComMemNotFoundException(exceptionMessage);
				}
			} else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}

	}

	@Override
	public AdmissionCommiteeMember viewCommiteeMember(int adminId) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("viewCommiteeMember " + adminId);
				Optional<AdmissionCommiteeMember> acmOptional = admissionCommiteeMemberRepository.findById(adminId);
				if (acmOptional.isPresent()) {
					return acmOptional.get();
				} else {
					String exceptionMessage = "CommiteeMember with adminId " + adminId + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new CourseNotFoundException(exceptionMessage);
				}
			} else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}

	}

	@Override
	public AdmissionCommiteeMember removeCommiteeMember(int adminId) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Removing CommiteeMember");
				Optional<AdmissionCommiteeMember> acmOptional = admissionCommiteeMemberRepository.findById(adminId);
				if (acmOptional.isPresent()) {
					AdmissionCommiteeMember acm = acmOptional.get();
					admissionCommiteeMemberRepository.delete(acm);
					return acm;
				} else {
					throw new AdmissionComMemNotFoundException("CommiteeMember with adminId " + adminId + " not found");
				}
			} else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}

	}

	@Override
	public List<AdmissionCommiteeMember> viewAllCommiteeMember() {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Getting all the commitee members");
				List<AdmissionCommiteeMember> acmList = admissionCommiteeMemberRepository.findAll();
				return acmList;
			} else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}

	}

}
