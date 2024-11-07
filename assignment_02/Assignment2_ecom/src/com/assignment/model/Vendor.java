package com.assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vendor {

	@Id
	private Integer id;

	
	public void setId(Integer id) {
		this.id = id;
	}


	private String name;

	
	private String city;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", city=" + city + "]";
	}

}
