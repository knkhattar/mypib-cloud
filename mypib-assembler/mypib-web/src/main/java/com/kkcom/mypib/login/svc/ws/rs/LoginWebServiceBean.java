package com.kkcom.mypib.login.svc.ws.rs;

public class LoginWebServiceBean {
	String userId;
	String password;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	String authenticated;

	/**
	 * @return the authenticated
	 */
	public String getAuthenticated() {
		return authenticated;
	}

	/**
	 * @param authenticated
	 *            the authenticated to set
	 */
	public void setAuthenticated(String authenticated) {
		this.authenticated = authenticated;
	}

}
