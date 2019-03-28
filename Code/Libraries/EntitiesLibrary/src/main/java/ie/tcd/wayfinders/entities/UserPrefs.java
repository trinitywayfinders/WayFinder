package ie.tcd.wayfinders.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserPrefs {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private int concernHealth = 1;
    private int concernSpeed = 2;
    private int concernCost = 3;
    private int concernPollutionAvoidance = 4;
    private int concernEmissionsReduction = 5;

    public static String USERNAME_KEY = "username";
    public static String HEALTH_KEY = "concernHealth";
    public static String SPEED_KEY = "concernSpeed";
    public static String COST_KEY = "concernCost";
    public static String POLLUTION_AVOIDANCE_KEY = "concernPollutionAvoidance";
    public static String EMISSIONS_REDUCTION_KEY = "concernEmissionsReduction";
    
    protected UserPrefs() {}
    
	public UserPrefs(int concernHealth, int concernSpeed, int concernCost,
			int concernPollutionAvoidance, int concernEmissionsReduction) {
		super();
		
		this.concernHealth = concernHealth;
		this.concernSpeed = concernSpeed;
		this.concernCost = concernCost;
		this.concernPollutionAvoidance = concernPollutionAvoidance;
		this.concernEmissionsReduction = concernEmissionsReduction;
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
