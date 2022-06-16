package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Admission;

public interface IAdmissionService {

	Admission addAdmission(Admission admission);
	Admission updateAdmission(Admission admission);
	Admission cancelAdmission(int admissionId);
	List<Admission> showAllAdmissionsByCourseId(int courseId);
} 
