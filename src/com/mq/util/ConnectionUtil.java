package com.mq.util;

import javax.jms.JMSException;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class ConnectionUtil {
	
	// Use a public IP if necessary
	private final static String HOST = "192.168.1.218";
	/* Depending on security measures, these may change. For this scenario,
		these are values used to log on to the server. For a real environment,
		do not refer to the password with a string.
	*/
	public final static String PASSWORD = "PASSWORD.1";
	public final static String USERNAME = "USER.1";
	// This value should be taken from MQ admins
	private final static int PORT = 1414;
	// The following values depend on your agreement with the other party.
	private final static String CHANNEL = "CHANNEL.1";
	private final static String QUEUE_MANAGER_NAME = "QM.1";
	public final static String INC_QUEUE_NAME = "INC.QUEUE.1";
	public final static String OUT_QUEUE_NAME = "OUT.QUEUE.1";
	
	public static JmsConnectionFactory getConnectionFactory() throws JMSException {
		JmsConnectionFactory cf = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER).createConnectionFactory();
		cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, HOST);
		cf.setIntProperty(WMQConstants.WMQ_PORT, PORT);
		cf.setStringProperty(WMQConstants.WMQ_CHANNEL, CHANNEL);
		cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
		cf.setStringProperty(WMQConstants.PASSWORD, PASSWORD);
		cf.setStringProperty(WMQConstants.USERID, USERNAME);
		cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, QUEUE_MANAGER_NAME);
		return cf;
	}
	
	// This method is for a specific purpose, it is not necessary for simple text message transfers.
	public static String readCharsIntoString(String hexaString) {
		StringBuilder builder = new StringBuilder();
		int counter = 1;
		int number = 0;
		for(char c: hexaString.toCharArray()){
			if(counter % 2 == 0) {
				number += Character.digit(c, 16);
				builder.append(Character.toString((char)number));
				number = 0;
			} else {
				number += Character.digit(c, 16) * 16;
			}
			counter++;
		}
		return builder.toString();
	}
	
}
