package com.kkcom.mypib.login.svc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kkcom.mypib.login.svc.LoginService;

public class LoginServiceImplHibernate implements LoginService {

	public boolean authenticate(String userid, String password) {
		System.out.println(" Impl:: " + this);

		// INSERT INTO LOGINDETAILS (USERID, PASSWORD) VALUES ('testid',
		// 'testpass')
		boolean isAuthenticated = false;

		final SessionFactory sessionFactory;
		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			System.out.println("sessionFactory :::" + sessionFactory);
			Session session = sessionFactory.openSession();

			Transaction transaction = null;

			try {
				transaction = session.beginTransaction();
				List list = session.createQuery(
						"from Login where name='" + userid
								+ "' and password = '" + password + "'").list();
				transaction.commit();
				System.out.println("commited");
				if (list.size() > 0) {
					isAuthenticated = true;
				}
			} catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		return isAuthenticated;
	}

	public void init() {
		System.out.println("init of ::" + this);
	}
}
