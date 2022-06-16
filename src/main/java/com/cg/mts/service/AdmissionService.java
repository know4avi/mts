package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Admission;
import com.cg.mts.exception.AdmissionNotFoundException;
import com.cg.mts.repository.IAdmissionRepository;

@Service
public class AdmissionService implements IAdmissionService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IAdmissionRepository admissionRepository;

	@Override
	public Admission addAdmission(Admission admission) {
		LOG.info("Adding admission");
		return admissionRepository.save(admission);
	}

	@Override
	public Admission updateAdmission(Admission admission) {
		LOG.info(admission.toString());
		Optional<Admission> admissionOptional = admissionRepository.findById(admission.getAdmissionId());

		if (admissionOptional.isPresent()) {
			return admissionRepository.save(admission);
		} else {
			String exceptionMessage = "Admission with admissionId " + admission.getAdmissionId() + " does not exist.";
			LOG.warn(exceptionMessage);
			throw new AdmissionNotFoundException(exceptionMessage);
		}
	}

	@Override
	public Admission cancelAdmission(int admissionId) {
		LOG.info("Cancelling admission");
		Optional<Admission> admissionOptional = admissionRepository.findById(admissionId);
		if (admissionOptional.isPresent()) {
			Admission admission = admissionOptional.get();
			admissionRepository.delete(admission);
			return admission;
		} else {
			throw new AdmissionNotFoundException("Admission with admissionId " + admissionId + " not found");
		}
	}

	@Override
	public List<Admission> showAllAdmissionsByCourseId(int courseId) {
		LOG.info("Getting admission information by courseId");
		List<Admission> admissionList = admissionRepository.findByCourseId(courseId);
		return admissionList;
	}

	@Override
	public List<Admission> showAllAdmissionsByDate(LocalDate admissionDate) {
		LOG.info("Getting admission information by admissionDate");
		List<Admission> admissionList = admissionRepository.findByAdmissionDate(admissionDate);
		return admissionList;
	}

}
