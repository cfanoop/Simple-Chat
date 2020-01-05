package com.cf.gepos.schat;

import java.util.Queue;

import com.cf.gepos.schat.conf.UserContext;
import com.cf.gepos.schat.sr.Message;
import com.cf.gepos.schat.sr.MessageReciever;
import com.cf.gepos.schat.sr.MessageSender;
import com.cf.gepos.schat.sr.ReadUserInput;

public class App {

	ReadUserInput rui = new ReadUserInput();
	MessageSender msgcenter = new MessageSender();

	MessageReciever msgRe = new MessageReciever();

	public static void main(String[] args) {
		new App().startApplication();
	}

	public void startApplication() {

		System.out.println("Starting schat for "+UserContext.getContext().getUserName());
		
		rui.start();

		msgcenter.startSending();
		msgRe.startRecieving();

		printMesg();

	}

	private void printMesg() {
		Queue<Message> msgQ = UserContext.getContext().getMsgContext().getRecievedMessages();
		while (true) {
			try {
				Thread.sleep(500);
				if (msgQ.isEmpty()) {
					continue;
				}
				//System.out.println("Message Printing");
				Message m = msgQ.poll();
				System.out.println(m.getUsername() + ":" + m.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
