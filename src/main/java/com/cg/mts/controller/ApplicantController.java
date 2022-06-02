package com.cg.mts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Applicant;
import com.cg.mts.service.ApplicantService;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicantService service;

	// http://localhost:8088/applicant/add-applicant
	@PostMapping("/add-applicant")
	public ResponseEntity<Applicant> addApplicant(@Valid @RequestBody Applicant applicant) {
		LOG.info(applicant.toString());
		Applicant app = service.addApplicant(applicant);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Applicant " + app.getApplicantId() + " was created successfully.");
		ResponseEntity<Applicant> response = new ResponseEntity<>(app, headers, HttpStatus.CREATED);
		return response;
	}

	// http://localhost:8088/applicant/update-applicant
	@PutMapping("/update-applicant")
	public ResponseEntity<Applicant> updateApplicant(@Valid @RequestBody Applicant applicant) {
		LOG.info(applicant.toString());
		Applicant app = service.updateApplicant(applicant);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Applicant " + app.getApplicantId() + " was updated successfully.");
		ResponseEntity<Applicant> response = new ResponseEntity<>(app, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/applicant/delete-applicant/{aid}
	@DeleteMapping("/delete-applicant/{aid}")
	public ResponseEntity<Applicant> deleteApplicant(@PathVariable(name = "aid") int applicantId) {
		LOG.info("Applicant deleted with applicantId= " + applicantId);
		Applicant app = service.deleteApplicant(applicantId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Applicant " + applicantId + " was deleted successfully.");
		ResponseEntity<Applicant> response = new ResponseEntity<>(app, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/applicant/view-applicant-by-id/{aid}
	@GetMapping("/view-applicant-by-id/{aid}")
	public ResponseEntity<Applicant> viewApplicantById(@PathVariable(name = "aid") int applicantId) {
		LOG.info(Integer.toString(applicantId));
		Applicant applicant = service.viewApplicant(applicantId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Applicant with applicantId " + applicantId + " was found successfully.");
		ResponseEntity<Applicant> response = new ResponseEntity<>(applicant, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/applicant/view-all-applicants
	@GetMapping("/view-all-applicants")
	public ResponseEntity<List<Applicant>> viewAllApplicants() {
		LOG.info("view-all-applicants");
		List<Applicant> applicantList = service.viewAllApplcants();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Applicants were found successfully.");
		ResponseEntity<List<Applicant>> response = new ResponseEntity<>(applicantList, headers, HttpStatus.OK);
		return response;
	}

}
