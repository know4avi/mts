package com.cg.mts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.MtsUser;
import com.cg.mts.service.MtsUserService;

@RestController
@RequestMapping("/user")
public class MtsUserController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MtsUserService appUserService;

	@GetMapping("/get-all-users")
	public ResponseEntity<List<MtsUser>> getAllAppUsers() {
		LOG.info("get-all-appUsers");
		return new ResponseEntity<List<MtsUser>>(appUserService.getAllUsers(), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<MtsUser> register(@RequestBody MtsUser appUser) {
		LOG.info(appUser.toString());
		return new ResponseEntity<MtsUser>(appUserService.registerUser(appUser), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<MtsUser> login(@RequestBody MtsUser appUser) {
		LOG.info(appUser.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "User " + appUser.getUserName() + " logged in successfully.");
		return new ResponseEntity<MtsUser>(appUserService.loginUser(appUser), headers, HttpStatus.OK);
	}

	@GetMapping("/logout/{user}")
	public ResponseEntity<String> logout(@PathVariable(name = "user") String userName) {
		LOG.info(userName);
		return new ResponseEntity<String>(appUserService.logoutUser(userName), HttpStatus.OK);
	}

	@PutMapping("/update-user")
	public ResponseEntity<MtsUser> updateAppUser(@RequestBody MtsUser appUser) {
		LOG.info(appUser.toString());
		return new ResponseEntity<MtsUser>(appUserService.updateUser(appUser), HttpStatus.OK);
	}
}
