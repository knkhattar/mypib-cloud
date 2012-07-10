package com.kkcom.mypib.login.svc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.kkcom.mypib.login.svc.LoginService;

public class LoginServiceImplLdap implements LoginService {

	public boolean authenticate(String userid, String password) {
		System.out.println(" Impl:: " + this);
		boolean retVal = false;
		if (userid.equals("testid") && password.equals("testpass")) {
			retVal = true;
		}

		return retVal;
	}

	public void init() {
		System.out.println("init of ::" + this);
	}
}
