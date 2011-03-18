package com.transformer.compiler;

public abstract class TaskStruct {
	private String taskId;
	private String[] inputPath;
	private String[] outputPath;
	private static int inputPathNum;
//	TaskStruct() {
//		this.taskId = id;
//		this.inputPath = inputPath;
//		this.outputPath = outputPath;
//	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String[] getInputPath() {
		return inputPath;
	}
	public void setInputPath(String[] inputPath) {
		this.inputPath = inputPath;
	}
	public String[] getOutputPath() {
		return outputPath;
	}
	public void setOutputPath(String[] outputPath) {
		this.outputPath = outputPath;
	}
	
	public abstract void operation(String[] inpath, String[] outpath);
	
}
