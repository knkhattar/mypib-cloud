package com.kkcom.mypib.login.svc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kkcom.mypib.login.svc.LoginService;
import com.kkcom.mypib.login.svc.model.Login;

public class LoginServiceImplDsJndi implements LoginService {

	public boolean authenticate(String userid, String password) {
		System.out.println(" Impl:: " + this);
		// Persist the user object here.
		boolean isAuthenticated = false;
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/logindb");
			System.out.println("ds::" + ds);
			connection = ds.getConnection();
			System.out.println("connection successful ::" + connection);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAuthenticated;
	}

	public void init() {
		System.out.println("init of ::" + this);
	}

}
