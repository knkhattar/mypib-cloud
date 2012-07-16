package com.kkcom.loginapp.svc;

import junit.framework.TestCase;

import com.kkcom.mypib.login.svc.LoginService;
import com.kkcom.mypib.login.svc.impl.LoginServiceImpl;
import com.kkcom.mypib.login.svc.impl.LoginServiceImplCrypto;
import com.kkcom.mypib.login.svc.impl.LoginServiceImplHibernate;
import com.kkcom.mypib.login.svc.impl.LoginServiceImplJdbc;

public class LoginServiceTest extends TestCase {

	LoginService loginService;

	public void testLoginServiceImpl() {
		loginService = new LoginServiceImpl();
		authenticate();
	}

	public void testLoginServiceImplHibernate() {
		loginService = new LoginServiceImplHibernate();
		authenticate();
	}

	public void testLoginServiceImplCrypto() {
		loginService = new LoginServiceImplCrypto();
		authenticate();

	}

	public void testLoginServiceImplDsSpring() {
		// TODO PENDING IMPLEMENTATION
	}

	public void testLoginServiceImplEjb() {
		// TODO PENDING IMPLEMENTATION
	}

	public void testLoginServiceImplJdbc() {
		loginService = new LoginServiceImplJdbc();
		authenticate();

	}

	public void testLoginServiceImplJms() {
		// TODO PENDING IMPLEMENTATION
	}

	public void testLoginServiceImplLdap() {
		// TODO PENDING IMPLEMENTATION
	}

	public void testLoginServiceImplDsJndi() {
		// TODO PENDING IMPLEMENTATION
	}

	public void testLoginServiceImplJaxWs() {
		// TODO PENDING IMPLEMENTATION
	}

	public void testLoginServiceImplJaxRs() {
		// TODO PENDING IMPLEMENTATION
	}

	private void authenticate() {
		boolean result = loginService.authenticate("testid", "testpass");
		assertTrue(result);
	}

	@Override
	protected void setUp() throws Exception {

	}
}