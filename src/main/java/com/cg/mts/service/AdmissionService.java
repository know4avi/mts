package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Admission;
import com.cg.mts.entities.Role;
import com.cg.mts.exception.AdmissionNotFoundException;
import com.cg.mts.exception.NotAuthorizedException;
import com.cg.mts.repository.IAdmissionRepository;

@Service
public class AdmissionService implements IAdmissionService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IAdmissionRepository admissionRepository;

	@Autowired
	private MtsUserService appUserService;

	@Override
	public Admission addAdmission(Admission admission) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Adding admission");
				return admissionRepository.save(admission);
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
	public Admission updateAdmission(Admission admission) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info(admission.toString());
				Optional<Admission> admissionOptional = admissionRepository.findById(admission.getAdmissionId());

				if (admissionOptional.isPresent()) {
					return admissionRepository.save(admission);
				} else {
					String exceptionMessage = "Admission with admissionId " + admission.getAdmissionId()
							+ " does not exist.";
					LOG.warn(exceptionMessage);
					throw new AdmissionNotFoundException(exceptionMessage);
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
	public Admission cancelAdmission(int admissionId) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Cancelling admission");
				Optional<Admission> admissionOptional = admissionRepository.findById(admissionId);
				if (admissionOptional.isPresent()) {
					Admission admission = admissionOptional.get();
					admissionRepository.delete(admission);
					return admission;
				} else {
					throw new AdmissionNotFoundException("Admission with admissionId " + admissionId + " not found");
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
	public List<Admission> showAllAdmissionsByCourseId(int courseId) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Getting admission information by courseId");
				List<Admission> admissionList = admissionRepository.findByCourseId(courseId);
				return admissionList;
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
	public List<Admission> showAllAdmissionsByDate(LocalDate admissionDate) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("Getting admission information by admissionDate");
				List<Admission> admissionList = admissionRepository.findByAdmissionDate(admissionDate);
				return admissionList;
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
