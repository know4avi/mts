package com.cg.mts.controller;

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

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.service.AdmissionCommiteeMemberService;

@RestController
@RequestMapping("/admission-commitee-member")
public class AdmissionCommiteeMemberController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdmissionCommiteeMemberService service;

	// http://localhost:8088/admission-commitee-member/add-member
	@PostMapping("/add-member")
	public ResponseEntity<AdmissionCommiteeMember> addMember(@Valid @RequestBody AdmissionCommiteeMember acm) {
		LOG.info(acm.toString());
		AdmissionCommiteeMember cm = service.addCommiteeMember(acm);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "AdmissionCommiteeMember " + cm.getAdminId() + " was created successfully.");
		ResponseEntity<AdmissionCommiteeMember> response = new ResponseEntity<>(cm, headers, HttpStatus.CREATED);
		return response;
	}

	// http://localhost:8088/admission-commitee-member/update-member
	@PutMapping("/update-member")
	public ResponseEntity<AdmissionCommiteeMember> updateMember(@Valid @RequestBody AdmissionCommiteeMember acm) {
		LOG.info(acm.toString());
		AdmissionCommiteeMember cm = service.updateCommiteeMember(acm);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "AdmissionCommiteeMember " + cm.getAdminId() + " was updated successfully.");
		ResponseEntity<AdmissionCommiteeMember> response = new ResponseEntity<>(cm, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/admission-commitee-member/delete-member/{aid}
	@DeleteMapping("/delete-member/{aid}")
	public ResponseEntity<AdmissionCommiteeMember> deleteMember(@PathVariable(name = "aid") int adminId) {
		LOG.info("AdmissionCommiteeMember deleted with adminId= " + adminId);
		AdmissionCommiteeMember acm = service.removeCommiteeMember(adminId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "AdmissionCommiteeMember " + adminId + " was deleted successfully.");
		ResponseEntity<AdmissionCommiteeMember> response = new ResponseEntity<>(acm, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/admission-commitee-member/view-member-by-id/{aid}
	@GetMapping("/view-member-by-id/{aid}")
	public ResponseEntity<AdmissionCommiteeMember> viewMemberById(@PathVariable(name = "aid") int adminId) {
		LOG.info(Integer.toString(adminId));
		AdmissionCommiteeMember acm = service.viewCommiteeMember(adminId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "AdmissionCommiteeMember with adminId " + adminId + " was found successfully.");
		ResponseEntity<AdmissionCommiteeMember> response = new ResponseEntity<>(acm, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/admission-commitee-member/view-all-members
	@GetMapping("/view-all-members")
	public ResponseEntity<List<AdmissionCommiteeMember>> viewAllMembers() {
		LOG.info("view-all-members");
		List<AdmissionCommiteeMember> acmList = service.viewAllCommiteeMember();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "AdmissionCommiteeMembers were found successfully.");
		ResponseEntity<List<AdmissionCommiteeMember>> response = new ResponseEntity<>(acmList, headers, HttpStatus.OK);
		return response;
	}
}
