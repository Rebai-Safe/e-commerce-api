package com.ecommerce.entity;

import java.util.Set;

import javax.persistence.*;





@Entity
@Table(name = "users")
public class User {
  
	@Id
	private String userName;
	private String userPassword;
	@OneToOne
	private UserProfile userProfile;
	@ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE",
	    joinColumns = { 
	    		@JoinColumn(name = "USER_ID")
	    		
	    },
	    inverseJoinColumns = {
	    		@JoinColumn(name = "ROLE_ID")
	    }
			)
	 private Set<Role> roles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
