package com.cg.mts.service;

public interface ILoginService {
	boolean loginAsApplicant(String s1, String s2);
	boolean loginAsdmissionCommiteeMember(String s1, String s2);
	boolean loginAsUniversityStaffMember(String s1, String s2);
}
