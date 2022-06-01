package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Course;

public interface ICourseService {
	Course addCourse(Course course);
	Course updateCourse(Course course);
	Course removeCourse(int courseId);
	Course viewCourse(int courseId);
	List<Course> viewAllCourses();
}
