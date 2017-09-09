package com.ab;

import java.lang.reflect.InvocationTargetException;

public class ABMain {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Handler.handle("Main.startup");
		Handler.handle("Main.startCommPort");
	}

}
