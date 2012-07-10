package com.kkcom.mypib.login.svc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.kkcom.mypib.login.svc.LoginService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LoginServiceImplJaxRs implements LoginService {

	public boolean authenticate(String userid, String password) {
		System.out.println(" Impl:: " + this);
		// Client client = Client.create();
		//
		// WebResource webResource = client
		// .resource("http://localhost:8080/mypib-web-110/rest/json/metallica/get");
		//
		// ClientResponse response = webResource.accept("application/json").get(
		// ClientResponse.class);
		//
		// if (response.getStatus() != 200) {
		// throw new RuntimeException("Failed : HTTP error code : "
		// + response.getStatus());
		// }
		//
		// String output = response.getEntity(String.class);
		//
		// System.out.println("Output from Server .... \n");
		// System.out.println(output);

		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8080/mypib-web/rest/json/metallica/post");

		String credentials = "{\"userId\":\"" + userid + "\","
				+ "\"password\":\"" + password + "\"}";

		// ClientResponse response =
		// webResource.accept("application/json").post(
		// ClientResponse.class,credentials);
		//
		ClientResponse response = webResource.type("application/json").post(
				ClientResponse.class, credentials);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		boolean isAuthenticated = false;
		try {
			// JSONArray jsonArray = new JSONArray(output);
			// jsonArray.
			JSONObject jsonObj = new JSONObject(output);
			String retVal = (String) jsonObj.get("authenticated");
			if (retVal.equals("true")) {
				isAuthenticated = true;
			}

		} catch (JSONException je) {
			je.printStackTrace();
		}

		System.out.println("Output from Server .... \n");
		System.out.println(output);

		return isAuthenticated;
	}

	public void init() {
		System.out.println("init of ::" + this);
	}
}
