package com.kkcom.loginapp.svc;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kkcom.mypib.login.svc.LoginService;
import com.kkcom.mypib.login.svc.impl.LoginServiceImpl;
import com.kkcom.mypib.login.svc.impl.LoginServiceImplCrypto;
import com.kkcom.mypib.login.svc.impl.LoginServiceImplHibernate;
import com.kkcom.mypib.login.svc.impl.LoginServiceImplJdbc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoginServiceTest extends TestCase {

	ApplicationContext context;

	@Override
	protected void setUp() throws Exception {
		this.context = new ClassPathXmlApplicationContext(
				"/WEB-INF/config/mypib-service-config.xml");

	}

	public void testLoginServiceImpl() {
		LoginService loginService = (LoginService) context
				.getBean("loginService");

		authenticate(loginService);
	}

	public void testLoginServiceImplHibernate() {
		LoginService loginService = (LoginService) context
				.getBean("loginServiceHibernate");

		authenticate(loginService);
	}

	public void testLoginServiceImplCrypto() {
		LoginService loginService = (LoginService) context
				.getBean("loginServiceCrypto");

		authenticate(loginService);

	}

	public void testLoginServiceImplDsSpring() {
		// TODO PENDING IMPLEMENTATION

		LoginService loginService = (LoginService) context
				.getBean("loginServiceDsSpring");
		authenticate(loginService);

	}

	public void testLoginServiceImplJdbc() {
		LoginService loginService = new LoginServiceImplJdbc();
		authenticate(loginService);

	}

	// public void testLoginServiceImplEjb() {
	// // TODO PENDING IMPLEMENTATION
	// }

	public void testLoginServiceImplJms() {
		// TODO PENDING IMPLEMENTATION
	}

	// public void testLoginServiceImplLdap() {
	// // TODO PENDING IMPLEMENTATION
	// }

	// public void testLoginServiceImplDsJndi() {
	// // TODO PENDING IMPLEMENTATION
	// }

	public void testLoginServiceImplJaxWs() {
		// TODO PENDING IMPLEMENTATION
	}

	public void testLoginServiceImplJaxRs() {
		// TODO PENDING IMPLEMENTATION
	}

	private void authenticate(LoginService loginService) {
		boolean result = loginService.authenticate("testid", "testpass");
		assertTrue(result);
	}

}