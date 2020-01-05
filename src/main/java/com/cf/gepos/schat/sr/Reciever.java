package com.cf.gepos.schat.sr;

import java.io.Serializable;

import com.cf.gepos.schat.conf.UserContext;

public class Reciever implements Serializable {

	private static final long serialVersionUID = 1L;

	private String recieverName;
	private String recieverIp;
	private int recieverPort;

	public Reciever(String recieverName) {
		UserContext uctx = UserContext.getContext();
		this.recieverName = recieverName;
		this.recieverIp = uctx.getProperties().getProperty("reciever." + recieverName + ".ip").trim();
		// System.out.println("recieverIp " + recieverIp);
		this.recieverPort = Integer
				.parseInt(uctx.getProperties().getProperty("reciever." + recieverName + ".port").trim());
	}

	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public String getRecieverIp() {
		return recieverIp;
	}

	public void setRecieverIp(String recieverIp) {
		this.recieverIp = recieverIp;
	}

	public int getRecieverPort() {
		return recieverPort;
	}

	public void setRecieverPort(int recieverPort) {
		this.recieverPort = recieverPort;
	}

}
