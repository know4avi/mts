package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.AdmissionCommiteeMember;

public interface IAdmissionCommiteeMemberService {
	
	AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember acm);
	AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember acm);
	AdmissionCommiteeMember viewCommiteeMember(int adminId);
	AdmissionCommiteeMember removeCommiteeMember(int adminId);
	List<AdmissionCommiteeMember> viewAllCommiteeMember();
}
