package com.kkcom.mypib.logoff.pres;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogOffController {


	/**
	 * Show account summary page
	 */
	@RequestMapping(value = "/logOff.htm", method = RequestMethod.GET)
	public String showAccountSummaryPage() {

		return "logOff";
	}

}
