package com.kkcom.mypib.login.svc.impl;


import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Replier implements MessageListener {

	public static void main(String args[]) throws Exception{
        Replier repl = Replier.newReplier(null, "mybank.mypib.Q.RQST", "mybank.mypib.Q.INVALID");
        
	}
	
	private Session session;
	private MessageProducer invalidProducer;
	private static ActiveMQConnectionFactory connectionFactory;

	protected Replier() {
		super();
	}

	public static Replier newReplier(Connection connection, String requestQueueName, String invalidQueueName)
		throws JMSException, NamingException {

		Replier replier = new Replier();
		replier.initialize(connection, requestQueueName, invalidQueueName);
		return replier;
	}

	protected void initialize(Connection connection, String requestQueueName, String invalidQueueName)
		throws NamingException, JMSException {

        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                ActiveMQConnection.DEFAULT_BROKER_URL);
		connection = connectionFactory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination requestQueue = session.createQueue(requestQueueName);
		Destination invalidQueue = session.createQueue(invalidQueueName);

		MessageConsumer requestConsumer = session.createConsumer(requestQueue);
		MessageListener listener = this;
		requestConsumer.setMessageListener(listener);

		invalidProducer = session.createProducer(invalidQueue);
	}

	public void onMessage(Message message) {
		try {
			if ((message instanceof TextMessage) && (message.getJMSReplyTo() != null)) {
				TextMessage requestMessage = (TextMessage) message;

				System.out.println("Received request");
				System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
				System.out.println("\tMessage ID: " + requestMessage.getJMSMessageID());
				System.out.println("\tCorrel. ID: " + requestMessage.getJMSCorrelationID());
				System.out.println("\tReply to:   " + requestMessage.getJMSReplyTo());
				System.out.println("\tContents:   " + requestMessage.getText());

				String contents = requestMessage.getText();
				Destination replyDestination = message.getJMSReplyTo();
				MessageProducer replyProducer = session.createProducer(replyDestination);

				TextMessage replyMessage = session.createTextMessage();
				final String isAuthenticated = authenticate(contents);
				replyMessage.setText(isAuthenticated);
				replyMessage.setJMSCorrelationID(requestMessage.getJMSMessageID());
				replyProducer.send(replyMessage);

				System.out.println("Sent reply");
				System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
				System.out.println("\tMessage ID: " + replyMessage.getJMSMessageID());
				System.out.println("\tCorrel. ID: " + replyMessage.getJMSCorrelationID());
				System.out.println("\tReply to:   " + replyMessage.getJMSReplyTo());
				System.out.println("\tContents:   " + replyMessage.getText());
			} else {
				System.out.println("Invalid message detected");
				System.out.println("\tType:       " + message.getClass().getName());
				System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
				System.out.println("\tMessage ID: " + message.getJMSMessageID());
				System.out.println("\tCorrel. ID: " + message.getJMSCorrelationID());
				System.out.println("\tReply to:   " + message.getJMSReplyTo());

				message.setJMSCorrelationID(message.getJMSMessageID());
				invalidProducer.send(message);

				System.out.println("Sent to invalid message queue");
				System.out.println("\tType:       " + message.getClass().getName());
				System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
				System.out.println("\tMessage ID: " + message.getJMSMessageID());
				System.out.println("\tCorrel. ID: " + message.getJMSCorrelationID());
				System.out.println("\tReply to:   " + message.getJMSReplyTo());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	final String authenticate (String s){
		final String userid = s.substring(0, s.indexOf(";"));
		final String password = s.substring(s.indexOf(";")+1, s.length());
		
		
		System.out.println(userid);
		System.out.println(password);
		return Boolean.toString(authenticate(userid, password));
	}
	
	
	public boolean authenticate(String userid, String password) {
		boolean isAuthenticated = false;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/logindb", "sa", "");
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT * FROM LOGINDETAILS WHERE USERID='"+userid+"' AND PASSWORD='"+password+"'");
			if (resultSet.next()){
				isAuthenticated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isAuthenticated;
	}
	
}

