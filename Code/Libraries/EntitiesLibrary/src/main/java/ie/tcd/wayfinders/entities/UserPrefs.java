package com.wayfinder.userprefs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserPrefs {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    private int concernHealth;
    private int concernSpeed;
    private int concernCost;
    private int concernPollutionAvoidance;
    private int concernEmissionsReduction;

    public static String USERNAME_KEY = "username";
    public static String HEALTH_KEY = "concernHealth";
    public static String SPEED_KEY = "concernSpeed";
    public static String COST_KEY = "concernCost";
    public static String POLLUTION_AVOIDANCE_KEY = "concernPollutionAvoidance";
    public static String EMISSIONS_REDUCTION_KEY = "concernEmissionsReduction";
    
    protected UserPrefs() {}
    
	public UserPrefs(String username, int concernHealth, int concernSpeed, int concernCost,
			int concernPollutionAvoidance, int concernEmissionsReduction) {
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
	public int getConcernHealth() {
		return concernHealth;
	}
	public int getConcernSpeed() {
		return concernSpeed;
	}
	public int getConcernCost() {
		return concernCost;
	}
	public int getConcernPollutionAvoidance() {
		return concernPollutionAvoidance;
	}
	public int getConcernEmissionsReduction() {
		return concernEmissionsReduction;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setConcernHealth(int concernHealth) {
		this.concernHealth = concernHealth;
	}

	public void setConcernSpeed(int concernSpeed) {
		this.concernSpeed = concernSpeed;
	}

	public void setConcernCost(int concernCost) {
		this.concernCost = concernCost;
	}

	public void setConcernPollutionAvoidance(int concernPollutionAvoidance) {
		this.concernPollutionAvoidance = concernPollutionAvoidance;
	}

	public void setConcernEmissionsReduction(int concernEmissionsReduction) {
		this.concernEmissionsReduction = concernEmissionsReduction;
	}
	
}
