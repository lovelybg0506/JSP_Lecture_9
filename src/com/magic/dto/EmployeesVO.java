package com.magic.dto;

import java.sql.Date;

public class EmployeesVO {
	private String id, pass, name, lev, phone, gender;
	private Date enter;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getEnter() {
		return enter;
	}
	public void setEnter(Date enter) {
		this.enter = enter;
	}
	
	@Override
	public String toString() {
		return "\n EmployeesVO[id="+id+", pass="+pass+", name="+name+", lev="+lev+", phone="+phone+", enter"+enter+", gender="+gender+"]";
	}

}
