package com.kkcom.mypib.login.svc;

import com.kkcom.mypib.login.svc.model.Login;

public interface LoginService {

	public void init();

	public boolean authenticate(String userid, String password);
}
