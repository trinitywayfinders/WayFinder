package ie.tcd.wayfinders.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="UserPrefs")
public class UserPrefs {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="userPrefsId")
	@JsonProperty("userPrefsId")
    private int userPrefsId;
    
	@Column(name="concernHealth", nullable=false, unique=false)
	@JsonProperty("concernHealth")
    private int concernHealth = 1;
	
	@Column(name="concernSpeed", nullable=false, unique=false)
	@JsonProperty("concernSpeed")
    private int concernSpeed = 2;
	
	@Column(name="concernCost", nullable=false, unique=false)
	@JsonProperty("concernCost")
    private int concernCost = 3;
	
	@Column(name="concernPollutionAvoidance", nullable=false, unique=false)
	@JsonProperty("concernPollutionAvoidance")
    private int concernPollutionAvoidance = 4;
	
	@Column(name="concernEmissionsReduction", nullable=false, unique=false)
	@JsonProperty("concernEmissionsReduction")
    private int concernEmissionsReduction = 5;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	@JsonIgnore
	private User user;

    public static String USERNAME_KEY = "username";
    public static String HEALTH_KEY = "concernHealth";
    public static String SPEED_KEY = "concernSpeed";
    public static String COST_KEY = "concernCost";
    public static String POLLUTION_AVOIDANCE_KEY = "concernPollutionAvoidance";
    public static String EMISSIONS_REDUCTION_KEY = "concernEmissionsReduction";
    
    public UserPrefs() {
    	
    }
    
    public UserPrefs(User user) {
    	this.user = user;
    }
    
    @JsonCreator
	public UserPrefs(@JsonProperty("concernHealth") int concernHealth, @JsonProperty("concernSpeed") int concernSpeed, @JsonProperty("concernCost") int concernCost, @JsonProperty("concernPollutionAvoidance") int concernPollutionAvoidance, @JsonProperty("concernEmissionsReduction") int concernEmissionsReduction) {		
		this.concernHealth = concernHealth;
		this.concernSpeed = concernSpeed;
		this.concernCost = concernCost;
		this.concernPollutionAvoidance = concernPollutionAvoidance;
		this.concernEmissionsReduction = concernEmissionsReduction;
	}
	
    public UserPrefs update(UserPrefs userPrefs) {
		this.concernHealth = userPrefs.getConcernHealth();
		this.concernSpeed = userPrefs.getConcernSpeed();
		this.concernCost = userPrefs.getConcernCost();
		this.concernPollutionAvoidance = userPrefs.getConcernPollutionAvoidance();
		this.concernEmissionsReduction = userPrefs.getConcernEmissionsReduction();
		
		return this;
	}
    
	public int getUserPrefsId() {
		return this.userPrefsId;
	}
	
	public void setId(int userPrefsId) {
		this.userPrefsId = userPrefsId;
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
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
