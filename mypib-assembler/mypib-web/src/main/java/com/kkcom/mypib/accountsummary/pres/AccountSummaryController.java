package com.kkcom.mypib.accountsummary.pres;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountSummaryController {

	/**
	 * Show account summary page
	 */
	@RequestMapping(value = "/accountSummary.htm", method = RequestMethod.GET)
	public String showAccountSummaryPage(HttpServletRequest request) {

		// NOTE : FOLLOWING CODE SHOWS PLAIN HTTP SESSION IMPLEMENTATION

		// HttpSession hs = request.getSession();
		// hs.setAttribute("username", "testid");
		// if(!request.getSession().getAttribute("username").toString().equals("testid")){
		// throw new Exception("INVALID SESSION");
		// }

		return "accountSummary";
	}

	/**
	 * Handles request for adding two numbers
	 */
	@RequestMapping(value = "/accountSummary.htm", method = RequestMethod.POST)
	public @ResponseBody
	String getBalance(Model model) {

		return "9999.88";
	}

}
