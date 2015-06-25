package com.mq.factory;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import com.mq.util.ConnectionUtil;

public class Consumer {
	
	MessageConsumer consumer = null;
	Session session = null;
	
	public Consumer(Session session, Listener listener) {
		this.session = session;
		try {
			this.consumer = session.createConsumer(session.createQueue(ConnectionUtil.INC_QUEUE_NAME));
			this.consumer.setMessageListener(listener);
		} catch(JMSException e) {
			throw new RuntimeException(e);
		}
	}

	public MessageConsumer getConsumer() {
		return consumer;
	}
}
