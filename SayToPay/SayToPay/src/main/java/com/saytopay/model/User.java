package com.saytopay.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

public class User {

    private String username;
    private String password;
    private PrivacyPolicy privacyPolicy;
    private List<Account> accounts;
    private String token;
    

    public PrivacyPolicy getPrivacyPolicy() {
		return privacyPolicy;
	}

	public void setPrivacyPolicy(PrivacyPolicy privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   

    public String toString() {
		return "username:" + username + ":" + "Password:" + password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
