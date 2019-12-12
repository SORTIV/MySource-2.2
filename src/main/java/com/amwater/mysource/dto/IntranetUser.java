package com.amwater.mysource.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apporchid.json.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IntranetUser implements Serializable{
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Address {
		@NotNull
		private String addressLine;
		@NotNull
		private String city;
		@NotNull
		@Size(min = 3, max = 3)
		private String country;
		@NotNull
		@Size(min = 2, max = 2)
		private String state;
		@NotNull
		private String zipCode;

		public String getAddressLine() {
			return addressLine;
		}

		public String getCity() {
			return city;
		}

		public String getCountry() {
			return country;
		}

		public String getState() {
			return state;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setAddressLine(String addressLine) {
			this.addressLine = addressLine;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public void setState(String state) {
			this.state = state;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Manager {
		private String name;
		private String userId;

		public String getName() {
			return name;
		}

		public String getUserId() {
			return userId;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class OrganizationRole {
		private String company;
		private String costCenter;
		private String department;
		private String designation;
		private String division;
		@JsonDeserialize(using = DateDeserializer.class)
		private Date doj;
		private String employeeManagerFlag;
		private String employeeNumber; 
		private Manager manager;
		private String role;
		private String unionNonUnion;

		public String getCompany() {
			return company;
		}

		public String getCostCenter() {
			return costCenter;
		}

		public String getDepartment() {
			return department;
		}

		public String getDesignation() {
			return designation;
		}

		public String getDivision() {
			return division;
		}

		public Date getDoj() {
			return doj;
		}

		public String getEmployeeManagerFlag() {
			return employeeManagerFlag;
		}

		public String getEmployeeNumber() {
			return employeeNumber;
		}

		public Manager getManager() {
			if(manager == null) {
				manager = new Manager();
			}
			return manager;
		}

		public String getRole() {
			return role;
		}
		
		public String getUnionNonUnion() {
			return unionNonUnion;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public void setCostCenter(String costCenter) {
			this.costCenter = costCenter;
		}


		public void setDepartment(String department) {
			this.department = department;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public void setDivision(String division) {
			this.division = division;
		}

		public void setDoj(Date doj) {
			this.doj = doj;
		}

		public void setEmployeeManagerFlag(String employeeManagerFlag) {
			this.employeeManagerFlag = employeeManagerFlag;
		}

		public void setEmployeeNumber(String employeeNumber) {
			this.employeeNumber = employeeNumber;
		}

		public void setManager(Manager manager) {
			this.manager = manager;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public void setUnionNonUnion(String unionNonUnion) {
			this.unionNonUnion = unionNonUnion;
		}

	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PersonalDetails {

		@NotNull
		private Address address;
		private String businessCellNumber;
		@NotNull

		private String dateOfBirth;
		@NotNull
		private String email;
		@NotNull
		private String firstname;
		@NotNull
		private char gender;
		
		@NotNull
		private String lastname;
		
		private String middlename;

		@NotNull
		private String phoneNumber;
		private String workPhoneNumber;

		public Address getAddress() {
			return address;
		}

		public String getBusinessCellNumber() {
			return businessCellNumber;
		}

		public String getDateOfBirth() {
			return dateOfBirth;
		}

		public String getEmail() {
			return email;
		}

		public String getFirstname() {
			return firstname;
		}

		public char getGender() {
			return gender;
		}

		public String getLastname() {
			return lastname;
		}

		public String getMiddlename() {
			return middlename;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public String getWorkPhoneNumber() {
			return workPhoneNumber;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public void setBusinessCellNumber(String businessCellNumber) {
			this.businessCellNumber = businessCellNumber;
		}

		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public void setGender(char gender) {
			this.gender = gender;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public void setMiddlename(String middlename) {
			this.middlename = middlename;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public void setWorkPhoneNumber(String workPhoneNumber) {
			this.workPhoneNumber = workPhoneNumber;
		}

	}

	private static final long serialVersionUID = 9150520688327487555L;

	@NotNull
	private String active;

	private Map<String,Set<String>> additionalProfileData;

	private String goals;
	
	private Set<String> groups;

	private Set<String> interests;
	
	@NotNull
	private OrganizationRole orginizationRole;

	@NotNull
	private PersonalDetails personalDetails;
	
	@NotNull
	private String userId;

	public Map<String, Set<String>> getAdditionalProfileData() {
		return additionalProfileData;
	}

	public String getGoals() {
		return goals;
	}

	public Set<String> getGroups() {
		if(this.groups == null) {
			this.groups = new HashSet<String>();
		}
		return groups;
	}

	public Set<String> getInterests() {
		if(interests == null) {
			interests = new HashSet<String>();
		}
		return interests;
	}

	public OrganizationRole getOrganizationRole() {
		if(orginizationRole == null) {
			orginizationRole = new OrganizationRole();
		}
		return orginizationRole;
	}

	public PersonalDetails getPersonalDetails() {
		if(personalDetails == null) {
			personalDetails = new PersonalDetails();
		}
		return personalDetails;
	}

	public String getUserId() {
		return userId;
	}

	public void setAdditionalProfileData(Map<String, Set<String>> additionalProfileData) {
		this.additionalProfileData = additionalProfileData;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public void setGroups(Set<String> groups) {
		this.groups = groups;
	}

	public void setInterests(Set<String> interests) {
		this.interests = interests;
	}

	public void setOrganizationRole(OrganizationRole organizationRole) {
		this.orginizationRole = organizationRole;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
