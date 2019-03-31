package com.test.mongo.sevis.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@org.springframework.data.mongodb.core.mapping.Document(collection = "sevis_person")
public class SevisPerson implements Serializable {
	
	@Id
	private String id;
	
	@NotNull
    @Size(min = 1, max = 50)
	private String firstName;
	
	@NotNull
    @Size(min = 1, max = 50)
    @Indexed	
	private String lastName;
	
	@NotNull
    private int age;
	
	@NotNull
    private String portOfEntry;
	
	@NotNull
    private String visaType;
	
	@NotNull
    private String entryDate;
    
    public SevisPerson() {
    	
    }
    
    public SevisPerson (String id, String firstName, String lastName, int age, String portOfEntry, String visaType, String entryDate) {
    	this.id = id;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.age = age;
    	this.portOfEntry = portOfEntry;
    	this.visaType = visaType;
    	this.entryDate = entryDate;
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPortOfEntry() {
		return portOfEntry;
	}
	public void setPortOfEntry(String portOfEntry) {
		this.portOfEntry = portOfEntry;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

    
    
}
