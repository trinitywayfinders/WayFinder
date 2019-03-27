package ie.tcd.wayfinders.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="User")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	@JsonProperty("userId")
	private int userId;
	
	@Column(name="username", nullable=false, unique=true)
	@JsonProperty("username")
	private String username;
	
	@Column(name="email", nullable=false, unique=true)
	@JsonProperty("email")
	private String email;
	
	@Column(name="password", nullable=false)
	@JsonProperty("password")
	private String password;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=false)
	private UserPrefs userPrefs;
		
	public User() {
		;
	}
	
	public User(@JsonProperty("username") String username, @JsonProperty("email") String email, @JsonProperty("password") String password) {
		this.username = username;
		this.email = email;	
		if(password != null && !password.startsWith("{noop}")) {
			this.password = "{noop}" + password;
		} else {
			this.password = password;
		}
	}
	
	@JsonCreator
	public User(@JsonProperty("userId") int userId, @JsonProperty("username") String username, @JsonProperty("email") String email, @JsonProperty("password") String password) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		if(password != null && !password.startsWith("{noop}")) {
			this.password = "{noop}" + password;
		} else {
			this.password = password;
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    ArrayList<Authority> authorities = new ArrayList<>();
	    
	    authorities.add(new Authority("ROLE_USER"));
	    
	    return authorities;
	}
	
	public UserPrefs getUserPrefs() {
		return userPrefs;
	}

	public void setUserPrefs(UserPrefs userPrefs) {
		this.userPrefs = userPrefs;
	}

	public int getUserId() {
		return this.userId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}