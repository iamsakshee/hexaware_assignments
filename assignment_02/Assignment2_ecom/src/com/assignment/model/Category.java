package com.assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {

	@Id
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	private String name;

	private int sequence;

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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", sequence=" + sequence + "]";
	}

}