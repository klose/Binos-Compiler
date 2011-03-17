package com.transformer.compiler;

public class Channel {
	private TransmitType trtype = TransmitType.localfile;
	private TaskStruct from;
	private TaskStruct to;
	private int outputIndex;
	private int inputIndex;
	public Channel(TaskStruct from, int outputIndex, TaskStruct to, int inputIndex){
		this.from = from;
		this.to  = to;
		this.inputIndex = inputIndex;
		this.outputIndex = outputIndex;
	}
	public Channel(TaskStruct from, int outputIndex, TaskStruct to, int inputIndex, TransmitType trtype){
		this.from = from;
		this.to  = to;
		this.inputIndex = inputIndex;
		this.outputIndex = outputIndex;
		this.trtype = trtype;
	}
}
