package com.transformer.compiler;

import java.util.ArrayList;

public class TaskStruct {
	private String taskId;
	private String[] inputPath;
	private String[] outputPath;
	private  int inputPathNum;
	private  int outputPathNum;
	private int depNum;
	private String[] depTaskId;
	private ArrayList<String> depTaskIdList = new ArrayList<String>();
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
	
	public void setInputPathNum(int inputPathNum){
		this.inputPathNum = inputPathNum;
	}
	public void setOutputPathNum(int outputPathNum){
		this.outputPathNum = outputPathNum;
	}
	public int getInputPathNum(){
		return this.inputPathNum;
	}
	public int getOutputPathNum(){
		return this.outputPathNum;
	}
	
	public void setDepNum(int depNum){
		this.depNum = depNum;
		
	}
	public int getDepNum(){
		return this.depNum;
	}
	
}
