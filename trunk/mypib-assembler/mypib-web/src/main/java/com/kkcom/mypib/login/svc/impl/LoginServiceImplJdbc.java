package com.kkcom.mypib.login.svc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.kkcom.mypib.login.svc.LoginService;

public class LoginServiceImplJdbc implements LoginService {

	public boolean authenticate(String userid, String password) {
		System.out.println(" Impl:: " + this);
		boolean isAuthenticated = false;
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(
					"jdbc:h2:tcp://localhost/~/logindb", "sa", "");
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT * FROM LOGINDETAILS WHERE USERID='"
							+ userid + "' AND PASSWORD='" + password + "'");
			if (resultSet.next()) {
				isAuthenticated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isAuthenticated;
	}

	public void init() {
		System.out.println("init of ::" + this);
	}
}
