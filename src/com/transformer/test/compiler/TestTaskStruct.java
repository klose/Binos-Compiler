package com.transformer.test.compiler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.transformer.compiler.TaskStruct;

public class TestTaskStruct extends TaskStruct {

	@Override
	public void operation(String[] inPath, String[] outPath) {
		// TODO Auto-generated method stub
		assert(inPath.length > 0);
//		File [] infile = new File[inpath.length];
//		FileReader reader;
//		FileWriter writer;
//		for(int i = 0; i < inpath.length; i++) {
//			infile[i] = new File(inpath[i]);
//			reader = new FileReader(infile[i]);
//			
//		}
//		File file = new File(inpath)
		System.out.println("inputpath:");
		for(String tmp: inPath) {
			System.out.print(tmp + " ");
		}
		System.out.println("\n" + "outputpath:" );
		for(String tmp: outPath) {
			System.out.print(tmp + " ");
		}
	}
	
}

