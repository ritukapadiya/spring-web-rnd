package com.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;


public class UserBean {
	int userID;
	@NotBlank(message = "This feild can't be empty")
	String firstName;
	@NotBlank(message = "This feild can't be empty")
	String email;
	@NotBlank(message = "This feild can't be empty")
	@Length(min = 5, message = "Password's min length must be 5")
	String password;
	@NotBlank(message = "This feild can't be empty")
	String gender;
	@NotBlank(message = "This feild can't be empty")
	String role;
	@NotBlank(message = "This feild can't be empty")
	String hobby;
	@NotBlank(message = "This feild can't be empty")
	String country;
	
	MultipartFile profile;
	String profilePath;
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public MultipartFile getProfile() {
		return profile;
	}

	public void setProfile(MultipartFile profile) {
		this.profile = profile;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}
	
	
	
}
