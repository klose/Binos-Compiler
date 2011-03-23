package com.transformer.test.compiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class TestTaskStruct1 {
	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
		ArrayList<String> inputArgs = new ArrayList<String> ();
		ArrayList<String> outputArgs = new ArrayList<String> ();
		boolean isInputArgs = false;
		System.out.println(args.length);
		assert(args.length < 2);
		String pathPattern = "(^\\.|^/|^[a-zA-Z])?:?/.+(/$)?";
		int i  = 0;
		assert((args[0].equals("-i") || args[0].equals("-o")) == true);
		while(i < args.length) {
			if(args[i].equals("-i")) {
				isInputArgs = true;
			}
			else if (args[i].equals("-o")) {
				isInputArgs = false;
			}
			else {
				assert(args[i].matches(pathPattern) == true);
				if(isInputArgs) {
					inputArgs.add(args[i]);
				}
				else {
					outputArgs.add(args[i]);
				}
			}
			i ++;
		}
		System.out.println(inputArgs.toString() + ":" + outputArgs.toString());
		Class invokeClass = Class.forName("com.transformer.test.compiler.TestTaskStruct");
		Method invokeMethod = invokeClass.getMethod("operate", String[].class, String[].class);
		invokeMethod.invoke(invokeClass.newInstance(), inputArgs.toArray(new String[0]), outputArgs.toArray(new String[0]));
		
	}
}
