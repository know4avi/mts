package com.cg.mts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Course;
import com.cg.mts.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseService service;

	// http://localhost:8088/course/add-course
	@PostMapping("/add-course")
	public ResponseEntity<Course> addCourse(@Valid @RequestBody Course course) {
		LOG.info(course.toString());
		Course crs = service.addCourse(course);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Course " + crs.getCourseId() + " was created successfully.");
		ResponseEntity<Course> response = new ResponseEntity<>(crs, headers, HttpStatus.CREATED);
		return response;
	}

	// http://localhost:8088/course/update-course
	@PutMapping("/update-course")
	public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course) {
		LOG.info(course.toString());
		Course crs = service.updateCourse(course);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Course " + crs.getCourseId() + " was updated successfully.");
		ResponseEntity<Course> response = new ResponseEntity<>(crs, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/course/delete-course/{cid}
	@DeleteMapping("/delete-course/{cid}")
	public ResponseEntity<Course> deleteCourse(@PathVariable(name = "cid") int courseId) {
		LOG.info("Course deleted with courseId= " + courseId);
		Course crs = service.removeCourse(courseId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Course " + courseId + " was deleted successfully.");
		ResponseEntity<Course> response = new ResponseEntity<>(crs, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/course/view-course-by-id/{cid}
	@GetMapping("/view-course-by-id/{cid}")
	public ResponseEntity<Course> viewCourseById(@PathVariable(name = "cid") int courseId) {
		LOG.info(Integer.toString(courseId));
		Course crs = service.viewCourse(courseId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Course with courseId " + courseId + " was found successfully.");
		ResponseEntity<Course> response = new ResponseEntity<>(crs, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/course/view-all-courses
	@GetMapping("/view-all-courses")
	public ResponseEntity<List<Course>> viewAllApplicants() {
		LOG.info("view-all-courses");
		List<Course> courseList = service.viewAllCourses();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Courses were found successfully.");
		ResponseEntity<List<Course>> response = new ResponseEntity<>(courseList, headers, HttpStatus.OK);
		return response;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach(error -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

}
