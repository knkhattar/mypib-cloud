package com.kkcom.loginapp.svc;

import com.kkcom.mypib.login.svc.LoginService;
import com.kkcom.mypib.login.svc.impl.LoginServiceImpl;
import com.kkcom.mypib.login.svc.impl.LoginServiceImplHibernate;

import junit.framework.TestCase;

public class LoginServiceTest extends TestCase {

	LoginService loginService;

	public void testLoginServiceImpl() {
		loginService = new LoginServiceImpl();
		boolean result = loginService.authenticate("testid", "testpass");
		assertTrue(result);
	}

	public void testLoginServiceImplHibernate() {
		loginService = new LoginServiceImplHibernate();
		boolean result = loginService.authenticate("testid", "testpass");
		assertTrue(result);
	}

	@Override
	protected void setUp() throws Exception {

	}
}