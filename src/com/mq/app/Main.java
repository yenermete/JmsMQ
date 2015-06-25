package com.mq.app;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import com.mq.factory.Consumer;
import com.mq.factory.Listener;
import com.mq.factory.Producer;
import com.mq.util.ConnectionUtil;

public class Main {

	public static void main(String[] args) throws JMSException {
		Connection connection = ConnectionUtil.getConnectionFactory().createConnection(ConnectionUtil.USERNAME, ConnectionUtil.PASSWORD);
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Producer producer = new Producer(session);
		producer.produceMessage("Sample message");
		Listener listener = new Listener(session);
		connection.setExceptionListener(listener);
		Consumer consumer = new Consumer(session, listener);
		while(true){}
	}

}
