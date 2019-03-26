package com.wayfinder.userprefs;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserPrefs {

	@Id
    private String username;
    private short concernHealth;
    private short concernSpeed;
    private short concernCost;
    private short concernPollutionAvoidance;
    private short concernEmissionsReduction;

    protected UserPrefs() {}
    
	public UserPrefs(String username, short concernHealth, short concernSpeed, short concernCost,
			short concernPollutionAvoidance, short concernEmissionsReduction) {
		super();
		this.username = username;
		this.concernHealth = concernHealth;
		this.concernSpeed = concernSpeed;
		this.concernCost = concernCost;
		this.concernPollutionAvoidance = concernPollutionAvoidance;
		this.concernEmissionsReduction = concernEmissionsReduction;
	}

	public String getUsername() {
		return username;
	}
	public short getConcernHealth() {
		return concernHealth;
	}
	public short getConcernSpeed() {
		return concernSpeed;
	}
	public short getConcernCost() {
		return concernCost;
	}
	public short getConcernPollutionAvoidance() {
		return concernPollutionAvoidance;
	}
	public short getConcernEmissionsReduction() {
		return concernEmissionsReduction;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setConcernHealth(short concernHealth) {
		this.concernHealth = concernHealth;
	}

	public void setConcernSpeed(short concernSpeed) {
		this.concernSpeed = concernSpeed;
	}

	public void setConcernCost(short concernCost) {
		this.concernCost = concernCost;
	}

	public void setConcernPollutionAvoidance(short concernPollutionAvoidance) {
		this.concernPollutionAvoidance = concernPollutionAvoidance;
	}

	public void setConcernEmissionsReduction(short concernEmissionsReduction) {
		this.concernEmissionsReduction = concernEmissionsReduction;
	}
	
}
