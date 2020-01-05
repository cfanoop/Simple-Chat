package com.cf.gepos.schat.conf;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.UUID;

public class UserContext {

	private String propsFileName = "mcp.properties";
	private static UserContext ucxt;
	private MessageContext msgContext = new MessageContext();
	private Properties properties = new Properties();

	private static void init() {
		try {
			ucxt = new UserContext();
			ucxt.loadProps();
			ucxt.userName = ucxt.properties.getProperty("idname");
			ucxt.userid = UUID.randomUUID().toString();
			ucxt.ip = InetAddress.getLocalHost().getHostAddress();
			ucxt.port = Integer.parseInt(ucxt.properties.getProperty("send.port").trim());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private void loadProps() {
		try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(propsFileName)) {
			properties.load(input);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private String userName;
	private String ip;
	private int port;
	private String userid;

	public MessageContext getMsgContext() {
		return msgContext;
	}

	public String getUserName() {
		return userName;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getUserid() {
		return userid;
	}

	public Properties getProperties() {
		return properties;
	}

	public static UserContext getContext() {
		if (ucxt == null)
			init();
		return ucxt;
	}

}
