package com.saytopay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.saytopay.model.Account;
import com.saytopay.model.CustomErrorType;
import com.saytopay.model.Login;
import com.saytopay.model.PrivacyPolicy;
import com.saytopay.model.User;
import com.saytopay.service.UserService;
import com.saytopay.util.Constants;


@Controller
public class APIController {
	
	@Autowired
	private UserService mUserService; //Service which will do all data retrieval/manipulation work
	
	private User mLoggedInUser;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> login(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password) {
		
		User user = mUserService.findByNameAndPwd(name, password);
		Login login = new Login();

		if (user != null) {
			login.setLoggedIn(true);
			mLoggedInUser = user;
		} else {
			login.setLoggedIn(false);
			login.setErrorMessage(Constants.INVALID_USER_PWD);
		}
		
		if(mLoggedInUser != null)
			System.out.println("Login:Logged in user:"+mLoggedInUser.getUsername());
		
		return new ResponseEntity<Login>(login, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Account>> accounts() {
		
		if(mLoggedInUser == null)
			return new ResponseEntity(new CustomErrorType(Constants.LOGGED_OUT_USER_ERROR), HttpStatus.NOT_FOUND);
		
		System.out.println("Account : Logged in user:"+mLoggedInUser.getUsername());
		
		return new ResponseEntity<List<Account>>(mUserService.getUserAccounts(mLoggedInUser.getUsername()), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/paybill", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> payBill(@RequestParam(value = "amount", required = true) String amount) {
		
		if(mLoggedInUser == null)
			return new ResponseEntity(new CustomErrorType(Constants.LOGGED_OUT_USER_ERROR), HttpStatus.NOT_FOUND);
		
		System.out.println("PAYBILL: Logged in user:"+mLoggedInUser.getUsername());
		System.out.println("Before payment : Account balance:"+mLoggedInUser.getAccounts().get(0).getÄccountBalance());
		
		Account acct = mLoggedInUser.getAccounts().get(0);
		acct.setÄccountBalance(""+ (Integer.parseInt(acct.getÄccountBalance())- Integer.parseInt(amount)));
		mLoggedInUser.getAccounts().remove(0);
		mLoggedInUser.getAccounts().add(0, acct);
		
		System.out.println("After payment: Account balance:"+mLoggedInUser.getAccounts().get(0).getÄccountBalance());
		
		mUserService.updateUser(mLoggedInUser);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPolicy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrivacyPolicy> policy() {
		if(mLoggedInUser == null)
			return new ResponseEntity(new CustomErrorType(Constants.LOGGED_OUT_USER_ERROR), HttpStatus.NOT_FOUND);
		return new ResponseEntity<PrivacyPolicy>(mLoggedInUser.getPrivacyPolicy(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updatePolicy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updatePolicy(@RequestParam(value = "askPayPin", required = true) boolean askPayPin, 
			@RequestParam(value = "showAccountBalance", required = true) boolean showAccountBalance,
			@RequestParam(value = "allowTransfers", required = true) boolean allowTransfers) {
		if(mLoggedInUser == null)
			return new ResponseEntity(new CustomErrorType(Constants.LOGGED_OUT_USER_ERROR), HttpStatus.NOT_FOUND);	
	
		PrivacyPolicy policy = new PrivacyPolicy();
		policy.setAskPaymentPIN(askPayPin);
		policy.setShowAccountBalance(showAccountBalance);
		policy.setShouldAllowTransfers(allowTransfers);
		mLoggedInUser.setPrivacyPolicy(policy);
		
		mUserService.updateUser(mLoggedInUser);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> logout() {
		mLoggedInUser = null;
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
