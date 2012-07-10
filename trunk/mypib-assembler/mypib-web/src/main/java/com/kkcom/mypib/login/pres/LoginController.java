package com.kkcom.mypib.login.pres;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.kkcom.mypib.login.svc.LoginService;
import com.kkcom.mypib.login.svc.model.Login;

@SuppressWarnings("deprecation")
public class LoginController extends SimpleFormController {

	static Logger logger = Logger.getLogger(LoginController.class);

	private LoginService loginService;

	public LoginController() {
		setCommandClass(Login.class);
		setCommandName("login");
	}

	public void setUserService(LoginService loginService) {
		this.loginService = loginService;
	}

	//@RequestMapping(method = RequestMethod.GET)
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
//		System.out.println("test::");
//		System.out.println("httpRequest"+ httpReq);
		Login login = (Login) command;
		String retVal = "loginFailure";
		try {
			boolean b = loginService.authenticate(login.getName(),
					login.getPassword());
			logger.error("is authenticated ::" + b);
			if (b) {
				retVal = "accountSummary";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return new ModelAndView(retVal);
	}

}
