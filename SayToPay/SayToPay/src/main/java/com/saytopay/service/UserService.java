package com.saytopay.service;


import java.util.List;

import com.saytopay.model.Account;
import com.saytopay.model.PrivacyPolicy;
import com.saytopay.model.User;

public interface UserService {
	
	User findByName(String name);
	
	User findByNameAndPwd(String name, String password);
	
	User findByToken(String token);
	
	void updateUser(User user);

	List<User> findAllUsers();
	
	void deleteAllUsers();
	
	List<Account> getUserAccounts(String username);
	
	PrivacyPolicy getUserPrivacyPolicy(String username);
	
}
