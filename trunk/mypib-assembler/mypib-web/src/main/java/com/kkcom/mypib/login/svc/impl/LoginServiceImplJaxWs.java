package com.kkcom.mypib.login.svc.impl;

import com.kkcom.mypib.login.svc.LoginService;
import com.kkcom.mypib.login.svc.ws.client.stub.LoginWebService;
import com.kkcom.mypib.login.svc.ws.client.stub.LoginWebServiceService;

public class LoginServiceImplJaxWs implements LoginService {

	public boolean authenticate(String userid, String password) {
		System.out.println(" Impl:: " + this);
		LoginWebServiceService loginService = new LoginWebServiceService();
		LoginWebService login = loginService.getLoginWebServicePort();

		return login.authenticate(userid, password);
	}

	public void init() {
		System.out.println("init of ::" + this);
	}
}
