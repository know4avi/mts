package com.cg.mts.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int courseId;
	
	@NotBlank(message = "Course Name is required")
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "course_duration")
	private String courseDuration;
	
	@Column(name = "course_start_date")
	private LocalDate courseStartDate;
	
	@Column(name = "course_end_date")
	private LocalDate courseEndDate;
	
	@Column(name = "course_fees")
	private String courseFees;
	
	public Course() {
	}

	public Course(int courseId, @NotBlank(message = "Course Name is required") String courseName, String courseDuration,
			LocalDate courseStartDate, LocalDate courseEndDate, String courseFees) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.courseFees = courseFees;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public LocalDate getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(LocalDate courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public LocalDate getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(LocalDate courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public String getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(String courseFees) {
		this.courseFees = courseFees;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDuration=" + courseDuration
				+ ", courseStartDate=" + courseStartDate + ", courseEndDate=" + courseEndDate + ", courseFees="
				+ courseFees + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseDuration, courseEndDate, courseFees, courseId, courseName, courseStartDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseDuration, other.courseDuration)
				&& Objects.equals(courseEndDate, other.courseEndDate) && Objects.equals(courseFees, other.courseFees)
				&& courseId == other.courseId && Objects.equals(courseName, other.courseName)
				&& Objects.equals(courseStartDate, other.courseStartDate);
	}
	
}
