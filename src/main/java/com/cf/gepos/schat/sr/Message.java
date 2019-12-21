package com.cf.gepos.schat.sr;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.UUID;

import com.cf.gepos.schat.conf.UserContext;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String text;
	private String username;
	private int port;
	private String hostIp;
	private LocalDateTime createdDate;
	private Reciever reciever;
	private static String ipaddr = "";

	public Message(String username, String text) {
		this.id = UUID.randomUUID().toString();
		this.username = username;
		this.createdDate = LocalDateTime.now();
		UserContext uctx = UserContext.getContext();
		this.port = uctx.getPort();
		this.hostIp = uctx.getIp();
		String[] mre = text.split(":");
		if (mre.length > 1) {
			this.text = mre[1];
			this.reciever = new Reciever(mre[0]);
		} else if (uctx.getMsgContext().getCurrentRece() != null) {
			this.text = text;
			this.reciever = uctx.getMsgContext().getCurrentRece();
		} else {
			throw new RuntimeException("No Reciever");
		}
		// System.out.println("Message Created");
	}

	public Reciever getReciever() {
		return reciever;
	}

	public void setReciever(Reciever reciever) {
		this.reciever = reciever;
	}

	public int getPort() {
		return port;
	}

	public String getHostIp() {
		return hostIp;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public byte[] getBytes() {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
			oos.flush();
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Message getMFromBytes(byte[] byteArray) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
			ObjectInput in = new ObjectInputStream(bis);
			Message m = (Message) in.readObject();
			// System.out.println("Message made " + m.text);
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
