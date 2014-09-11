package com.java.main;

public class State {
	private String name;
	private String abbreviation;
	private String region;
	public String resourceId;

	public State() {
		// TODO Auto-generated constructor stub
	}

	public State(String name, String abbreviation, String region, String resourceFilePath) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.region = region;
		this.resourceId = resourceFilePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}
