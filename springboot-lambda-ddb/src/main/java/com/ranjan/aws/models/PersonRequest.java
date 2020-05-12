package com.ranjan.aws.models;

public class PersonRequest {
	
	private int id;
	private String firstName;
    private String lastName;
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "PersonRequest [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
