package com.cg.mts.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admissions")
public class Admission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admission_id")
	private int admissionId;
	
	@Column(name = "course_id")
	private int courseId;
	
	@Column(name = "applicant_id")
	private int applicantId;
	
	@Column(name = "admission_date")
	private LocalDate admissionDate;
	
	public Admission() {
		
	}

	public Admission(int admissionId, int courseId, int applicantId, LocalDate admissionDate) {
		this.admissionId = admissionId;
		this.courseId = courseId;
		this.applicantId = applicantId;
		this.admissionDate = admissionDate;
	}

	public int getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	@Override
	public String toString() {
		return "Admission [admissionId=" + admissionId + ", courseId=" + courseId + ", applicantId=" + applicantId
				+ ", admissionDate=" + admissionDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(admissionDate, admissionId, applicantId, courseId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admission other = (Admission) obj;
		return Objects.equals(admissionDate, other.admissionDate) && admissionId == other.admissionId
				&& applicantId == other.applicantId && courseId == other.courseId;
	}
	
}
