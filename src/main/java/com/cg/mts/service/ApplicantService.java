package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.AdmissionStatus;
import com.cg.mts.entities.Applicant;
import com.cg.mts.repository.IApplicantRepository;

@Service
public class ApplicantService implements IApplicantService{

	@Autowired
	IApplicantRepository applicantRepository;
	
	@Override
	public Applicant addApplicant(Applicant applicant) {
		return applicantRepository.save(applicant);
	}

	@Override
	public Applicant updateApplicant(Applicant applicant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant deleteApplicant(Applicant applicant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant viewApplicant(int applicantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Applicant> viewAllApplcantsByStatus(AdmissionStatus ad) {
		// TODO Auto-generated method stub
		return null;
	}

}
