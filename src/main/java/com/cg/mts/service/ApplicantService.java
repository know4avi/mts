package com.cg.mts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Applicant;
import com.cg.mts.entities.Role;
import com.cg.mts.exception.ApplicantNotFoundException;
import com.cg.mts.exception.NotAuthorizedException;
import com.cg.mts.repository.IApplicantRepository;

@Service
public class ApplicantService implements IApplicantService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IApplicantRepository applicantRepository;

	@Autowired
	private MtsUserService appUserService;

	@Override
	public Applicant addApplicant(Applicant applicant) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Adding applicant");
				return applicantRepository.save(applicant);
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
	public Applicant updateApplicant(Applicant applicant) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info(applicant.toString());
				Optional<Applicant> applicantOptional = applicantRepository.findById(applicant.getApplicantId());

				if (applicantOptional.isPresent()) {
					return applicantRepository.save(applicant);
				} else {
					String exceptionMessage = "Applicant with applicantId " + applicant.getApplicantId()
							+ " does not exist.";
					LOG.warn(exceptionMessage);
					throw new ApplicantNotFoundException(exceptionMessage);
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
	public Applicant deleteApplicant(int applicantId) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Applicant removed");
				Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
				if (applicantOptional.isPresent()) {
					Applicant app = applicantOptional.get();
					applicantRepository.delete(app);
					return app;
				} else {
					throw new ApplicantNotFoundException("Applicant with applicantId " + applicantId + " not found");
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
	public Applicant viewApplicant(int applicantId) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("getApplicantById " + applicantId);
				Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
				if (applicantOptional.isPresent()) {
					return applicantOptional.get();
				} else {
					String exceptionMessage = "Applicant with applicantId " + applicantId + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new ApplicantNotFoundException(exceptionMessage);
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
	public List<Applicant> viewAllApplcants() {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Getting all the applicants");
				List<Applicant> applicantList = applicantRepository.findAll();
				return applicantList;
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
