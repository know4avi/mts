package com.cg.mts.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Admission;
import com.cg.mts.service.AdmissionService;

@RestController
@RequestMapping("/admission")
public class AdmissionController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdmissionService service;

	// http://localhost:8088/admission/add-admission
	@PostMapping("/add-admission")
	public ResponseEntity<Admission> addAdmission(@Valid @RequestBody Admission admission) {
		LOG.info(admission.toString());
		Admission adm = service.addAdmission(admission);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Admission " + adm.getAdmissionId() + " was created successfully.");
		ResponseEntity<Admission> response = new ResponseEntity<>(adm, headers, HttpStatus.CREATED);
		return response;
	}

	// http://localhost:8088/admission/update-admission
	@PutMapping("/update-admission")
	public ResponseEntity<Admission> updateAdmission(@Valid @RequestBody Admission admission) {
		LOG.info(admission.toString());
		Admission adm = service.updateAdmission(admission);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Admission " + adm.getAdmissionId() + " was updated successfully.");
		ResponseEntity<Admission> response = new ResponseEntity<>(adm, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/admission/delete-admission/{aid}
	@DeleteMapping("/delete-admission/{aid}")
	public ResponseEntity<Admission> cancelAdmission(@PathVariable(name = "aid") int admissionId) {
		LOG.info("Admission cancelled with admissionId= " + admissionId);
		Admission adm = service.cancelAdmission(admissionId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Admission " + admissionId + " was cancelled successfully.");
		ResponseEntity<Admission> response = new ResponseEntity<>(adm, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/admission/view-all-admissions-by-courseid
	@GetMapping("/view-all-admissions-by-courseid/{aid}")
	public ResponseEntity<List<Admission>> viewAllAdmissionsByCourseId(@PathVariable(name = "aid") int courseId) {
		LOG.info("view-all-applicants");
		List<Admission> admissionList = service.showAllAdmissionsByCourseId(courseId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Admissions were found successfully.");
		ResponseEntity<List<Admission>> response = new ResponseEntity<>(admissionList, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/admission/view-all-admissions-by-courseid
	@GetMapping("/view-all-admissions-by-admissiondate/{adate}")
	public ResponseEntity<List<Admission>> viewAllAdmissionsByAdmissionDate(@PathVariable(name = "adate") LocalDate admissionDate) {
		LOG.info("view-all-applicants");
		List<Admission> admissionList = service.showAllAdmissionsByDate(admissionDate);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Admissions were found successfully.");
		ResponseEntity<List<Admission>> response = new ResponseEntity<>(admissionList, headers, HttpStatus.OK);
		return response;
	}
}
