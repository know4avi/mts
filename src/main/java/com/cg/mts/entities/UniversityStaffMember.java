package com.cg.mts.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "universityStaffMembers")
public class UniversityStaffMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	private int staffId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	public UniversityStaffMember() {
		
	}

	public UniversityStaffMember(int staffId, String password, String role) {
		super();
		this.staffId = staffId;
		this.password = password;
		this.role = role;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UniversityStaffMember [staffId=" + staffId + ", password=" + password + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, role, staffId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UniversityStaffMember other = (UniversityStaffMember) obj;
		return Objects.equals(password, other.password) && Objects.equals(role, other.role) && staffId == other.staffId;
	}
	
}
