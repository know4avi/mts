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
@Table(name = "admissionCommiteeMembers")
public class AdmissionCommiteeMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private int adminId;

	@NotBlank(message = "Admin Name is required")
	@Column(name = "admin_name")
	private String adminName;

	@Column(name = "admin_contact")
	private String adminContact;

	public AdmissionCommiteeMember() {

	}

	public AdmissionCommiteeMember(int adminId, @NotBlank(message = "Admin Name is required") String adminName,
			String adminContact) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminContact = adminContact;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	@Override
	public String toString() {
		return "AdmissionCommiteeMember [adminId=" + adminId + ", adminName=" + adminName + ", adminContact="
				+ adminContact + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminContact, adminId, adminName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdmissionCommiteeMember other = (AdmissionCommiteeMember) obj;
		return Objects.equals(adminContact, other.adminContact) && adminId == other.adminId
				&& Objects.equals(adminName, other.adminName);
	}

}
