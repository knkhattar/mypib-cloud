package com.kkcom.mypib.login.svc.impl;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.kkcom.mypib.login.svc.LoginService;

//As of now LoginServiceImplJms is not able to listen back
//when run as a service - but if run as a java application it listens back to replier.
//TODO Make it run as a service 
public class LoginServiceImplJms implements LoginService {
	private static ActiveMQConnectionFactory connectionFactory;
	private static Connection connection;
	private static Destination destination;
	private static boolean transacted = false;

	private Session session;
	private Destination replyQueue;
	private MessageProducer requestProducer;
	private MessageConsumer replyConsumer;
	private MessageProducer invalidProducer;
	private String requestQueueName = "mybank.mypib.Q.RQST";
	private String replyQueueName = "mybank.mypib.Q.RESP";
	private String invalidQueueName = "mybank.mypib.Q.INVALID";

    public static void main(String [] args){
    	LoginServiceImplJms jms = new LoginServiceImplJms();
    	jms.init();
    	jms.authenticate("testiwd","testpass");
    }
	
	public void init() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,
					ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination requestQueue = session.createQueue(requestQueueName);
			replyQueue = session.createQueue(replyQueueName);
			Destination invalidQueue = session.createQueue(invalidQueueName);

			requestProducer = session.createProducer(requestQueue);
			replyConsumer = session.createConsumer(replyQueue);
			invalidProducer = session.createProducer(invalidQueue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean authenticate(String userid, String password) {
		boolean retVal = false;

		try {
			send(userid, password);
			String strReply = receiveSync();
			retVal = Boolean.valueOf(strReply);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}

	private void send(String userid, String password) throws JMSException {
		TextMessage requestMessage = session.createTextMessage();
		requestMessage.setText(userid + ";" + password);
		requestMessage.setJMSReplyTo(replyQueue);
		requestProducer.send(requestMessage);
		System.out.println("Sent request");
		System.out.println("\tTime:       " + System.currentTimeMillis()
				+ " ms");
		System.out.println("\tMessage ID: " + requestMessage.getJMSMessageID());
		System.out.println("\tCorrel. ID: "
				+ requestMessage.getJMSCorrelationID());
		System.out.println("\tReply to:   " + requestMessage.getJMSReplyTo());
		System.out.println("\tContents:   " + requestMessage.getText());
	}

	private String receiveSync() throws JMSException {
		Message msg = replyConsumer.receive();
		final String reply;
		if (msg instanceof TextMessage) {
			TextMessage replyMessage = (TextMessage) msg;
			System.out.println("Received reply ");
			System.out.println("\tTime:       " + System.currentTimeMillis()
					+ " ms");
			System.out.println("\tMessage ID: "
					+ replyMessage.getJMSMessageID());
			System.out.println("\tCorrel. ID: "
					+ replyMessage.getJMSCorrelationID());
			System.out.println("\tReply to:   " + replyMessage.getJMSReplyTo());
			reply = replyMessage.getText();
			System.out.println("\tContents:   " + replyMessage.getText());
		} else {
			System.out.println("Invalid message detected");
			System.out.println("\tType:       " + msg.getClass().getName());
			System.out.println("\tTime:       " + System.currentTimeMillis()
					+ " ms");
			System.out.println("\tMessage ID: " + msg.getJMSMessageID());
			System.out.println("\tCorrel. ID: " + msg.getJMSCorrelationID());
			System.out.println("\tReply to:   " + msg.getJMSReplyTo());

			msg.setJMSCorrelationID(msg.getJMSMessageID());
			invalidProducer.send(msg);

			System.out.println("Sent to invalid message queue");
			System.out.println("\tType:       " + msg.getClass().getName());
			System.out.println("\tTime:       " + System.currentTimeMillis()
					+ " ms");
			System.out.println("\tMessage ID: " + msg.getJMSMessageID());
			System.out.println("\tCorrel. ID: " + msg.getJMSCorrelationID());
			System.out.println("\tReply to:   " + msg.getJMSReplyTo());
			reply = "false";
		}

		return reply;
	}

}
