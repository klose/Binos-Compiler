package com.transformer.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public  class TaskStruct {
	private String taskId;
	private String[] inputPath;
	private String[] outputPath;
	private  int inputPathNum;
	private  int outputPathNum;
	private int depNum;
	private String[] depTaskId;
	private ArrayList<String> depTaskIdList = new ArrayList<String>();
	private Map<String,Integer> depTaskMap = new HashMap<String, Integer>();
//	TaskStruct() {
//		this.taskId = id;
//		this.inputPath = inputPath;
//		this.outputPath = outputPath;
//	}
	public TaskStruct(){
		
	}
	public TaskStruct(TaskStruct ts){
		this.depNum = ts.getDepNum();
		this.inputPath = ts.getInputPath();
		this.outputPath = ts.getOutputPath();
		this.inputPathNum = ts.getInputPathNum();
		this.outputPathNum = ts.getOutputPathNum();
		
	}
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
		this.inputPath = new String[inputPath.length];
		this.inputPath = inputPath;
	}
	public String[] getOutputPath() {
		return outputPath;
	}
	public void setOutputPath(String[] outputPath) {
		this.outputPath = outputPath;
	}
//	public abstract void operation(String[] inpath, String[] outpath);
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
		this.depTaskId = new String[depNum];
		Iterator it = this.depTaskIdList.iterator();
		int i = 0;

		while(it.hasNext()){
			if(i+1 > depNum){
				System.err.println("wrong in depdence task number");
				System.exit(2);
			}
			this.depTaskId[i] = (String)it.next();
			
			i++;
		}
	}
	public int getDepNum(){
		return this.depNum;
	}
	public String[] getDepId(){
		return this.depTaskId;
	}
	
	public void addMap(String taskId, int outputIndex){
		this.depTaskMap.put(taskId, Integer.valueOf(outputIndex));
	}
	
	public void addDepTaskId(String id){
		this.depTaskIdList.add(id);
	}
}
