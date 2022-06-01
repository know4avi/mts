package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.AdmissionStatus;
import com.cg.mts.entities.Applicant;

public interface IApplicantService {
	Applicant addApplicant(Applicant applicant);
	Applicant updateApplicant(Applicant applicant);
	Applicant deleteApplicant(Applicant applicant);
	Applicant viewApplicant(int applicantId);
	List<Applicant> viewAllApplcantsByStatus(AdmissionStatus ad);
}
