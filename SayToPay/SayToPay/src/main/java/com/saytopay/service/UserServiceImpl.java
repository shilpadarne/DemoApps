package com.saytopay.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.saytopay.model.Account;
import com.saytopay.model.PrivacyPolicy;
import com.saytopay.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;
	
	private static List<User> tokenBasedUsers;

	static {
		users = populateDummyUsers();
		tokenBasedUsers = populateTokenUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}

	public User findByName(String name) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public User findByNameAndPwd(String name, String password) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(name) && user.getPassword().equalsIgnoreCase(password)) {
				return user;
			}
		}
		return null;
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		System.out.println("User index:" + index);
		users.set(index, user);
	}

	public void deleteAllUsers() {
		users.clear();
	}

	@Override
	public List<Account> getUserAccounts(String username) {
		return findByName(username).getAccounts();
	}

	@Override
	public PrivacyPolicy getUserPrivacyPolicy(String username) {
		return findByName(username).getPrivacyPolicy();
	}

	@Override
	public User findByToken(String token) {
		for (User user : users) {
			if (user.getToken().equalsIgnoreCase(token)) {
				return user;
			}
		}
		return null;
	}


	private static List<User> populateDummyUsers() {

		PrivacyPolicy policy = new PrivacyPolicy();
		
		// Set user data
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUsername("Shilpa");
		user.setPassword("shilpa@123");
		user.setToken("123456789");
		user.setPrivacyPolicy(new PrivacyPolicy());
		user.setAccounts(getAccounts());
		users.add(user);

		user = new User();
		user.setUsername("Amin");
		user.setPassword("amin@123");
		user.setToken("123456788");
		policy.setAskPaymentPIN(true);
		user.setPrivacyPolicy(policy);
		user.setAccounts(getAccounts());
		users.add(user);

		user = new User();
		user.setUsername("Mohsin");
		user.setPassword("mohsin@123");
		user.setToken("123456787");
		user.setPrivacyPolicy(new PrivacyPolicy());
		user.setAccounts(getAccounts());
		users.add(user);

		user = new User();
		user.setUsername("Yogesh");
		user.setPassword("yogesh@123");
		user.setToken("123456786");
		user.setPrivacyPolicy(new PrivacyPolicy());
		user.setAccounts(getAccounts());
		users.add(user);

		return users;
	}
	
	private static List<User> populateTokenUsers() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUsername("Thomas");
		user.setPassword("thomas@123");
		user.setToken("123456789");
		user.setPrivacyPolicy(new PrivacyPolicy());
		user.setAccounts(getAccounts());
		users.add(user);
		
		user = new User();
		user.setUsername("Alexa");
		user.setPassword("alexa@123");
		user.setToken("987654321");
		user.setPrivacyPolicy(new PrivacyPolicy());
		user.setAccounts(getAccounts());
		users.add(user);

		return users;
	}

	private static List<Account> getAccounts() {
		// init accounts data
		List<Account> accounts = new ArrayList<>();
		Account account = new Account();
		account.setAcctName("Savings account");
		account.setAcctNumber("xxx xxx 1206");
		account.setAccountBalance("890000");
		accounts.add(account);

		account = new Account();
		account.setAcctName("Savings account");
		account.setAcctNumber("xxx xxx 2001");
		account.setAccountBalance("1000000");
		accounts.add(account);

		account = new Account();
		account.setAcctName("Savings account");
		account.setAcctNumber("xxx xxx 2017");
		account.setAccountBalance("800000");
		accounts.add(account);

		account = new Account();
		account.setAcctName("Savings account");
		account.setAcctNumber("xxx xxx 1110");
		account.setAccountBalance("85000");
		accounts.add(account);

		return accounts;
	}
}
