package com.wayfinder.auth.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
		
	public User() {
		;
	}
	
	public User(String username, String email, String password) {
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
		return new ArrayList<GrantedAuthority>();
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