package com.mq.factory;


import javax.jms.BytesMessage;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.mq.util.ConnectionUtil;

public class Listener implements MessageListener, ExceptionListener {

	MessageProducer producer = null;
	Session session = null;
	
	public Listener(Session session){
		try {
			this.session = session;
			this.producer = session.createProducer(session.createQueue(ConnectionUtil.OUT_QUEUE_NAME));
		} catch(JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public void onMessage(Message message) {
		if(message instanceof TextMessage) {
			try {
				producer.send(session.createTextMessage(((TextMessage)message).getText()));
			} catch(JMSException e) {
				e.printStackTrace();
			}
		} else if(message instanceof BytesMessage) {
			try {
				TextMessage textMessage = session.createTextMessage(ConnectionUtil.readCharsIntoString(message.toString()));
				producer.send(textMessage);
			} catch(JMSException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Wrong message!");
		}
		System.out.println(message);
	}


	@Override
	public void onException(JMSException e) {
		e.printStackTrace();
	}
	
}
