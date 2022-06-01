package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;

public interface IUniversityStaffService {
	
	UniversityStaffMember addStaff(UniversityStaffMember usm);
	UniversityStaffMember updateStaff(UniversityStaffMember usm);
	UniversityStaffMember viewStaff(int staffId);
	UniversityStaffMember removeStaff(int staffId);
	List<UniversityStaffMember> viewAllStaffs();
	Course addCourse(Course course);
	Course updateCourse(Course course);
	Course removeCourse(int courseId);
}
