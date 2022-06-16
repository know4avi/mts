package com.cg.mts.service;

import java.util.List;
import com.cg.mts.entities.Course;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.repository.ICourseRepository;

@Service
public class CourseService implements ICourseService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ICourseRepository courseRepository;

	@Override
	public Course addCourse(Course course) {
		LOG.info("Adding course");
		return courseRepository.save(course);
	}

	@Override
	public Course updateCourse(Course course) {
		LOG.info(course.toString());
		Optional<Course> courseOptional = courseRepository.findById(course.getCourseId());

		if (courseOptional.isPresent()) {
			return courseRepository.save(course);
		} else {
			String exceptionMessage = "Course with courseId " + course.getCourseId() + " does not exist.";
			LOG.warn(exceptionMessage);
			throw new CourseNotFoundException(exceptionMessage);
		}
	}

	@Override
	public Course removeCourse(int courseId) {
		LOG.info("Removing Course");
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		if (courseOptional.isPresent()) {
			Course course = courseOptional.get();
			courseRepository.delete(course);
			return course;
		} else {
			throw new CourseNotFoundException("Course with courseId " + courseId + " not found");
		}
	}

	@Override
	public Course viewCourse(int courseId) {
		LOG.info("viewCourseById " + courseId);
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		if (courseOptional.isPresent()) {
			return courseOptional.get();
		} else {
			String exceptionMessage = "Course with courseId " + courseId + " does not exist.";
			LOG.warn(exceptionMessage);
			throw new CourseNotFoundException(exceptionMessage);
		}
	}

	@Override
	public List<Course> viewAllCourses() {
		LOG.info("Getting all the courses");
		List<Course> courseList = courseRepository.findAll();
		return courseList;
	}

}
