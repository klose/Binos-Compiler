package com.transformer.compiler;

/**
 * This is base operation that can be used.
 * @author jiangbing
 *
 */
public interface Operation {
	public void operate(JobProperties properties, String[] inputPath, String[] outputPath);
}
