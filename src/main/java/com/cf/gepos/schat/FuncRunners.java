package com.cf.gepos.schat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FuncRunners {

	private static int MAX_THREADS = 2;
	public static ExecutorService stuff = Executors.newFixedThreadPool(MAX_THREADS);

}
