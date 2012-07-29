package com.kkcom.mypib.login.svc.impl;

import com.kkcom.mypib.MyPibUtils;
import com.kkcom.mypib.constants.MyPibConstants;
import com.kkcom.mypib.login.svc.LoginService;

public class LoginServiceImplCrypto implements LoginService {

	public boolean authenticate(String userid, String password) {
		System.out.println(" Impl:: " + this);
		boolean retVal = false;
		try {
			if (MyPibUtils.getEncryptedValue(userid, MyPibConstants.CRYPTO_SEED).equals(
					MyPibConstants.USERID_ENCRYPTED)
					&& MyPibUtils.getEncryptedValue(password, MyPibConstants.CRYPTO_SEED).equals(
							MyPibConstants.PASSWORD_ENCRYPTED)) {
				retVal = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public void init() {
		System.out.println("init of ::" + this);
	}

}
