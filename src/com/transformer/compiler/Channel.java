package com.transformer.compiler;

public class Channel {
	private TransmitType transType;	
	private TaskStruct from;
	private TaskStruct to;
	private int outputIndex;
	private int inputIndex;
	public Channel(TaskStruct from, int outputIndex, TaskStruct to, int inputIndex){
		this.from = from;
		this.to  = to;
		this.inputIndex = inputIndex;
		this.outputIndex = outputIndex;
		transType = TransmitType.HDFS;// set the default value
	}
	public TaskStruct getFrom(){
		return this.from;
	}
	public TaskStruct getTo(){
		return this.to;
	}
	public int getOutputIndex(){
		return this.outputIndex;
	}
	public int getInputIndex(){
		return this.inputIndex;
	}
	public TransmitType getTransType() {
		return transType;
	}
	public void setTransType(TransmitType transType) {
		this.transType = transType;
	}
}
