package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Admission;
import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.entities.Applicant;

public interface IAdmissionCommiteeMemberService {
	
	AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember acm);
	AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember acm);
	AdmissionCommiteeMember viewCommiteeMember(int adminId);
	void removeCommiteeMember(int adminId);
	List<AdmissionCommiteeMember> viewAllCommiteeMember();
	void provideAdmissionResult(Applicant applicant, Admission admission);
}
