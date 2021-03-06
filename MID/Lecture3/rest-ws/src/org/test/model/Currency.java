package org.test.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlRootElement(name="currency")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {
	private int ID;
	private String name;
	private String country;
	private int yearAdopted;
	
	
	public Currency(){
		
	}
public Currency(String country, String name, int yearAdopted, int ID){
		this.ID = ID;
		this.name = name;
		this.country = country;
		this.yearAdopted = yearAdopted;
	}
	
	public int getID() {
	return ID;
	}
	public void setID(int ID) {
	this.ID = ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getYearAdopted() {
		return yearAdopted;
	}
	public void setYearAdopted(int yearAdopted) {
		this.yearAdopted = yearAdopted;
	}
	
}

