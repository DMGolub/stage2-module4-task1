package com.mjc.stage2;

public class ThreadSafeSingleton {

	private static volatile ThreadSafeSingleton instance;

	private ThreadSafeSingleton() {
		// empty
	}

	public static ThreadSafeSingleton getInstance() {
		if (instance == null) {
			synchronized (ThreadSafeSingleton.class) {
				if (instance == null) {
					instance = new ThreadSafeSingleton();
				}
			}
		}
		return instance;
	}
}