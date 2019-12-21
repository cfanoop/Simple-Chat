package com.cf.gepos.schat.sr;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import com.cf.gepos.schat.FuncRunners;
import com.cf.gepos.schat.conf.UserContext;

public class MessageSender {

	private UserContext uctxt;

	public MessageSender() {
		this.dsQ = new PriorityQueue<DatagramSocket>(Comparator.comparing(DatagramSocket::hashCode));
		addDS();
		uctxt = UserContext.getContext();
	}

	private Queue<DatagramSocket> dsQ;

	public void startSending() {

		FuncRunners.stuff.submit(this::send);
	}

	private void send() {

		Queue<Message> inps = uctxt.getMsgContext().getInputs();
		while (true) {
			try {
				Thread.sleep(500);
				if (inps.isEmpty()) {
					continue;
				}
				Message m = inps.poll();
				byte[] b = m.getBytes();
				InetAddress iaddr = InetAddress.getByName(m.getReciever().getRecieverIp());
				DatagramPacket dpSend = new DatagramPacket(b, b.length, iaddr, m.getReciever().getRecieverPort());
				DatagramSocket ds = dsQ.poll();
				ds.send(dpSend);
				// System.out.println("Send");
				dsQ.offer(ds);
				UserContext.getContext().getMsgContext().setReciever(m.getReciever());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void addDS() {
		for (int i = 0; i < 10; i++) {
			try {
				dsQ.add(new DatagramSocket());
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
	}

}
