package com.kkcom.mypib.login.svc.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class LoginWebService {

	@WebMethod(operationName = "authenticate")
	public boolean authenticate(String userid, String password) {
		boolean retVal = false;
		if (userid != null && password != null && userid.equals("testid")
				&& password.equals("testpass")) {
			retVal = true;
		}

		return retVal;
	}

}
