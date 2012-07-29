package com.kkcom.mypib.login.svc.impl;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.InitialDirContext;

import com.kkcom.mypib.login.svc.LoginService;

public class LoginServiceImplLdap implements LoginService {

	private InitialDirContext ctx;

	public boolean authenticate(String userid, String password) {
		System.out.println(" Impl:: " + this);

		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");

			// replace with your server URL/IP
			env.put(Context.PROVIDER_URL, "LDAP://XYZXYZ:389");

			/*
			 * only DIGEST-MD5 works with our Windows Active Directory
			 */
			env.put(Context.SECURITY_AUTHENTICATION, "DIGEST-MD5");

			env.put(Context.SECURITY_PRINCIPAL, userid);
			env.put(Context.SECURITY_CREDENTIALS, password);
			ctx = new InitialDirContext(env);
			return true;
		} catch (Exception ex) {
			System.out.println("Authenticating user - " + userid + " failed");
		} finally {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception ex) {
				}
			}
		}
		return false;
	}

	public void init() {
		System.out.println("init of ::" + this);
	}
}
