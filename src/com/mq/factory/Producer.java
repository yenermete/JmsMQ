package com.mq.factory;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.mq.util.ConnectionUtil;

public class Producer {
	MessageProducer producer = null;
	Session session = null;
	
	public Producer(Session session){
		this.session = session;
		try {
			producer = session.createProducer(session.createQueue(ConnectionUtil.OUT_QUEUE_NAME));
		} catch(JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void produceMessage(String text) throws JMSException {
		producer.send(session.createTextMessage(text + " This message was created on " + System.currentTimeMillis()));
	}
	
	public void closeConnections(){
		if (producer != null) {
			try {
				producer.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		if (session != null) {
			try {
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
