package com.example.demo.model;

import java.util.Objects;

public class Fruit {

	private String name;

	private String description;

	public Fruit() {

	}

	public Fruit(String name, String desc) {
		this.name = name;
		this.description = desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Fruit)) {
			return false;
		}

		Fruit other = (Fruit) obj;

		return Objects.equals(other.name, this.name);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.name);
	}

}
