package com.kkcom.mypib.contactdetails.pres;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactDetailsController {

	@RequestMapping(value = "/contactDetails.htm", method = RequestMethod.GET)
	public String showContactDetailsPage() throws Exception{
		return "contactDetails";
	}

}
