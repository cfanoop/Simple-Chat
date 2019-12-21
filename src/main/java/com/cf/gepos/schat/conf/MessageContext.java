package com.cf.gepos.schat.conf;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import com.cf.gepos.schat.sr.Message;
import com.cf.gepos.schat.sr.Reciever;

public class MessageContext {

	private Comparator<Message> c = Comparator.comparing(Message::getCreatedDate);

	private Queue<Message> inputs = new PriorityQueue<Message>(c);

	private Queue<Message> recievedMessages = new PriorityQueue<Message>(c);

	private Reciever currentRece;

	public Queue<Message> getInputs() {
		return inputs;
	}

	public Queue<Message> getRecievedMessages() {
		return recievedMessages;
	}

	public Reciever getCurrentRece() {
		return currentRece;
	}

	public void setCurrentRece(Reciever currentRece) {
		this.currentRece = currentRece;
	}

	public synchronized void setReciever(Reciever reciever) {
		currentRece = reciever;

	}

}
