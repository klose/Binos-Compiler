package com.transformer.compiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TaskStructClassGene {
	private String className;
	public TaskStructClassGene(String className) {
		this.className = className;
	}
	public String getClassName() {
		return this.className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * print the help about args.
	 */
	public static void printUsage() {
		System.out.println(
				"Usage: " + " [className] [-i inputPath ...] [-o outputPath ...] "+"\n");
		System.exit(-1);
	}
	public static void main(String[] args)  {
		Integer.valueOf(i); 
		if(args.length <= 5) {
			printUsage();
		}
		TaskStructClassGene tscg = new TaskStructClassGene(args[0]);
		try {
			Class cls = Class.forName(tscg.getClassName());
			ArrayList<String> inputArgs = new ArrayList<String> ();
			ArrayList<String> outputArgs = new ArrayList<String> ();
			boolean isInputArgs = false;
			String pathPattern = "(^\\.|^/|^[a-zA-Z])?:?/.+(/$)?";
			int i  = 1;
			while(i < args.length) {
				if(args[i].equals("-i")) {
					isInputArgs = true;
				}
				else if (args[i].equals("-o")) {
					
					isInputArgs = false;
				}
				else {
					if(!args[i].matches(pathPattern)){
						printUsage();
					}
					if(isInputArgs) {
						inputArgs.add(args[i]);
					}
					else {
						outputArgs.add(args[i]);
					}
				}
				i ++;
			}
			if( (inputArgs.size() + outputArgs.size()) == 0) {
				printUsage();
			}
			Method m = cls.getMethod("operation", String[].class, String[].class);
			m.invoke(cls.newInstance(), inputArgs.toArray(new String[0]), 
					outputArgs.toArray(new String[0]));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
