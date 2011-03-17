package com.transformer.compiler;

public class PhaseStruct {
	
	public PhaseStruct() {
		
	}
	/**
	 * add the first level's task Struct or last level's task struct. 
	 * In the first level, define path as the inputPath.
	 * In the last level, define path as the outputPath.
	 * @param ts
	 * @param parallelNum
	 * @param inputPath
	 */
	public void addTask(TaskStruct ts, int parallelNum, String[] path) {
		
	}
	/**
	 * add the middle phase in job. These tasks are neither in the first level, 
	 * nor in the last level.
	 * @param ts
	 * @param parallelNum
	 */
	public void addTask(TaskStruct ts, int parallelNum) {
		
	}
	
}
