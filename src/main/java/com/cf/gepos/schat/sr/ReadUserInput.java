package com.cf.gepos.schat.sr;

import java.util.Scanner;

import com.cf.gepos.schat.FuncRunners;
import com.cf.gepos.schat.conf.UserContext;

public class ReadUserInput {

	private Scanner scanner;

	private UserContext uctx = UserContext.getContext();

	public ReadUserInput() {
		scanner = new Scanner(System.in);
	}

	public void start() {

		FuncRunners.stuff.submit(this::poll);

	}

	private void poll() {
		while (true) {
			if (scanner.hasNextLine()) {
				try {
					// System.out.println("Reading");
					String txt = scanner.nextLine();
					Message ui = new Message(uctx.getUserName(), txt);
					uctx.getMsgContext().getInputs().add(ui);
					// System.out.println("Value Entered");
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public UserContext getUctx() {
		return uctx;
	}

}
