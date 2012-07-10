package com.kkcom.mypib.login.svc.ws.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json/metallica")
public class LoginWebServiceRestImpl {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginWebServiceBean getTrackInJSON() {

		LoginWebServiceBean bean = new LoginWebServiceBean();
		bean.setAuthenticated("true");
		return bean;

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginWebServiceBean authenticate(final LoginWebServiceBean bean) {

		LoginWebServiceBean resultBean = new LoginWebServiceBean();
		if (bean.userId.equals("testid") && bean.password.equals("testpass")) {
			resultBean.setAuthenticated("true");
		} else {
			resultBean.setAuthenticated("false");
		}

		return resultBean;
	}

}
