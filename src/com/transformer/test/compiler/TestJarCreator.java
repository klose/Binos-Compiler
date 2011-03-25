package com.transformer.test.compiler;

import java.io.IOException;
import java.util.jar.JarFile;

import com.transformer.compiler.JarCreator;

public class TestJarCreator {
	public static void main(String[] args) throws IOException {
		String sourcePath = "/home/jiangbing/Transformer/bin/";
		String mainClass = "com.transformer.compiler.TaskStructClassGene";
		String jarPath = "/tmp/1_2_4.jar";
		JarCreator jc = new JarCreator(sourcePath, mainClass, jarPath);
		jc.createJar();
	}
}
