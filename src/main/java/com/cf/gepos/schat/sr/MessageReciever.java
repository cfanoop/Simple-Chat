package com.cf.gepos.schat.sr;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Queue;

import com.cf.gepos.schat.FuncRunners;
import com.cf.gepos.schat.conf.UserContext;

public class MessageReciever {

	private DatagramSocket ds;
	private Queue<Message> recievedMessages;

	public MessageReciever() {
		try {
			this.ds = new DatagramSocket(
					Integer.parseInt(UserContext.getContext().getProperties().getProperty("recieve.port").trim()));
			recievedMessages = UserContext.getContext().getMsgContext().getRecievedMessages();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startRecieving() {

		FuncRunners.stuff.submit(this::read);
	}

	private void read() {

		byte[] byteArray = new byte[100000];

		DatagramPacket dpRead = null;
		while (true) {
			try {
				dpRead = new DatagramPacket(byteArray, byteArray.length);
				ds.receive(dpRead);
				Message m = Message.getMFromBytes(byteArray);
				// System.out.println("Message Recieved");
				recievedMessages.add(m);
				Thread.sleep(500);
				byteArray = new byte[100000];
				UserContext.getContext().getMsgContext().setReciever(new Reciever(m.getUsername()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
