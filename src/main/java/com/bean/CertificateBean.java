package com.bean;

public class CertificateBean {
	String name,email,course,mentor,monthYear;
 
	public CertificateBean() {} 
	public CertificateBean(String name,String email,String course,String mentor, String monthYear) {
		this.name = name;
		this.course=course;
		this.email=email;
		this.mentor=mentor;
		this.monthYear=monthYear;
	}
		
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getMentor() {
		return mentor;
	}

	public void setMentor(String mentor) {
		this.mentor = mentor;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	
}
