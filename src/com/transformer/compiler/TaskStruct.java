package com.transformer.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;



public  class TaskStruct {
	private String taskId;
	private String[] inputPath;
	private String[] outputPath;
	private Vector<Channel> inputChannel = new Vector<Channel>();
	private Vector<Channel> outputChannel = new Vector<Channel>();
	private  int inputPathNum;
	private  int outputPathNum;
	private int depNum;
	private String[] depTaskId;
	private String className;//set the name of class which implements the interface---Operation. 
	private ArrayList<String> depTaskIdList = new ArrayList<String>();
	private Map<String,Integer> depTaskMap = new HashMap<String, Integer>();
	private String taskXmlPath;// this is relative path in the directory of job
	private String taskJarPath; // this is relative path in the directory of job
	private Class <? extends Operation> operCls;
	private String localXMLPath; //this is local path of the taskId.xml
	
	/*specify whether the input is configured by job, when the value is true,job specify the input file.*/  
	private boolean configInput = false; 
	
	//specify whether the output is configured by job.
	private boolean configOutput = false; 
	
	public TaskStruct(){
		
	}
	public TaskStruct(TaskStruct ts){
		this.depNum = ts.getDepNum();
		this.inputPath = ts.getInputPath();
		this.outputPath = ts.getOutputPath();
		this.inputPathNum = ts.getInputPathNum();
		this.outputPathNum = ts.getOutputPathNum();
		this.className = ts.getClassName();
	}
	public void setOperationClass(Class<? extends Operation> cls) {
		this.operCls = cls;
		this.setClassName(cls.getName());
	} 
	public String getClassName() {
		return this.className;
	}
	private void setClassName(String className) {
		this.className = className;
	}
	
	public String getLocalXMLPath() {
		return localXMLPath;
	}
	public void setLocalXMLPath(String localXMLPath) {
		this.localXMLPath = localXMLPath + "/" + this.taskId + "/" + this.taskId + ".xml";;
	}
	public String getTaskId() {
		return taskId;
		
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
		this.taskXmlPath = this.taskId + "/" + this.taskId + ".xml";
		this.taskJarPath = "job.jar";		
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
	public String getTaskXmlPath(){
		return this.taskXmlPath;
	}
	public String getTaskJarPath(){
		return this.taskJarPath;
	}
	/**
	 * Set the relative path of the task.xml.
	 * This can be mapped into HDFS.
	 * @param dirPath: this is the base path of the job.
	 */
	public void setTaskXmlRelativePath(String dirPath){
		this.taskXmlPath = dirPath + "/" + this.taskXmlPath;
	}
	
	/**
	 * Set the relative path of the task jar 
	 * This can be mapped into HDFS.
	 * @param dirPath: this is the base path of the job.
	 */
	public void setTaskJarRelativePath(String dirPath){
		this.taskJarPath = dirPath+ "/" + "job.jar";
	}
	public void addMap(String taskId, int outputIndex){
		if(this.depTaskMap.containsKey(taskId)){
			taskId = taskId + " ";
		}
		this.depTaskMap.put(taskId, Integer.valueOf(outputIndex));
	}
	
	public void addDepTaskId(String id){
		if(!this.depTaskIdList.contains(id)){
			this.depTaskIdList.add(id);
		}
		
	}
	public void addInputChannel(Channel channel) {
		this.inputChannel.add(channel);
	}
	public void addOutputChannel(Channel channel) {
		this.outputChannel.add(channel);
	}
	
	public Vector<Channel> getInputChannel() {
		return inputChannel;
	}
	public Vector<Channel> getOutputChannel() {
		return outputChannel;
	}
	public Map<String,Integer> getDepTaskMap(){
		return this.depTaskMap;
	}
	public ArrayList<String> getDepTaskIdList(){
		return this.depTaskIdList;
	}
	public boolean isConfigInput() {
		return configInput;
	}
	public void setConfigInput(boolean configInput) {
		this.configInput = configInput;
	}
	public boolean isConfigOutput() {
		return configOutput;
	}
	public void setConfigOutput(boolean configOutput) {
		this.configOutput = configOutput;
	}
	
}
