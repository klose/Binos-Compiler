package com.transformer.test.compiler;

import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TestJarFile {
	public static void main(String[] args) throws IOException {
		JarFile file = new JarFile("/tmp/1_2_4.jar");
		JarEntry entry = file.getJarEntry("");
		
	}
}
