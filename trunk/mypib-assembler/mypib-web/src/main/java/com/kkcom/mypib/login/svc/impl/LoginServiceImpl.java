package com.kkcom.mypib.login.svc.impl;

import com.kkcom.mypib.constants.MyPibConstants;
import com.kkcom.mypib.login.svc.LoginService;

public class LoginServiceImpl implements LoginService {

	public boolean authenticate(String userid, String password) {
		System.out.println(" Impl:: " + this);
		boolean retVal = false;
		if (userid.equals(MyPibConstants.USERID_TEST_VALUE) && password.equals(MyPibConstants.PASSWORD_TEST_VALUE)) {
			retVal = true;
		}

		return retVal;
	}

	public void init() {
		System.out.println("init of ::" + this);
	}

}
