package com.cg.mts.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "applicants")
public class Applicant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicant_id")
	private int applicantId;

	@NotBlank(message = "Applicant Name is required")
	@Column(name = "applicant_name")
	private String applicantName;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "applicant_degree")
	private String applicantDegree;

	@Column(name = "applicant_graduation_percent")
	private String applicantGraduationPercent;

	public Applicant() {

	}

	public Applicant(int applicantId, @NotBlank(message = "Applicant Name is required") String applicantName,
			String mobileNumber, String applicantDegree, String applicantGraduationPercent) {
		super();
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.mobileNumber = mobileNumber;
		this.applicantDegree = applicantDegree;
		this.applicantGraduationPercent = applicantGraduationPercent;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getApplicantDegree() {
		return applicantDegree;
	}

	public void setApplicantDegree(String applicantDegree) {
		this.applicantDegree = applicantDegree;
	}

	public String getApplicantGraduationPercent() {
		return applicantGraduationPercent;
	}

	public void setApplicantGraduationPercent(String applicantGraduationPercent) {
		this.applicantGraduationPercent = applicantGraduationPercent;
	}

	@Override
	public String toString() {
		return "Applicant [applicantId=" + applicantId + ", applicantName=" + applicantName + ", mobileNumber="
				+ mobileNumber + ", applicantDegree=" + applicantDegree + ", applicantGraduationPercent="
				+ applicantGraduationPercent + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicantDegree, applicantGraduationPercent, applicantId, applicantName, mobileNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applicant other = (Applicant) obj;
		return Objects.equals(applicantDegree, other.applicantDegree)
				&& Objects.equals(applicantGraduationPercent, other.applicantGraduationPercent)
				&& applicantId == other.applicantId && Objects.equals(applicantName, other.applicantName)
				&& Objects.equals(mobileNumber, other.mobileNumber);
	}

}
