package com.transformer.test.compiler;

import java.io.IOException;

import com.transformer.compiler.JarResolver;

public class TestJarResolver {
	public static void main(String[] args) throws IOException {
		String jarPath = "/tmp/1_2_4.jar";
		String toDir = "/tmp/1_2_4_unpack";
		JarResolver jarResolver = new JarResolver(jarPath, toDir);
		jarResolver.unJar();
		
	}
}
