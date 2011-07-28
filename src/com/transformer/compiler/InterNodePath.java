package com.transformer.compiler;

import java.util.Vector;

public class InterNodePath {
	private static String pathPrefix;
	public static String[] partitionInputPath(TaskStruct ts) {
		int inputNum = ts.getInputPathNum();
		String[] tmppath = new String[inputNum];
		Vector<Channel> inputChannels = ts.getInputChannel();
		for (int i = 0; i < inputNum; i++) {
			Channel channel = inputChannels.get(i);
			TaskStruct formerTask;
			switch (channel.getTransType()) {
			//if transmit type is HDFS, it is a definite input path, which is no matter about what to use scheduler.
			case HDFS:
//				pathPrefix = JobConfiguration.getPathHDFSPrefix();
//				tmppath[i] = pathPrefix+ "/" + ts.getTaskId() +"inputPath"+i;
				formerTask = channel.getFrom();
				tmppath[i] = formerTask.getTaskId() + "::outputPath::" + formerTask.getOutputChannel().indexOf(channel);
				break;
			//if transmit type is HTTP, which means using Local File + Remote Read， so it relies on the scheduler.
			case HTTP:
				formerTask = channel.getFrom();
				tmppath[i] = formerTask.getTaskId() + "::outputPath::" + formerTask.getOutputChannel().indexOf(channel);
				break;
			//if transmit type is MSG, which means using Msg from one Task to this one, and it is dependent upon former task. 
			case MSG:
				pathPrefix = JobConfiguration.getMsgHeader();
				formerTask = channel.getFrom();
				tmppath[i] = pathPrefix + formerTask.getTaskId() + "::outputPath::" + formerTask.getOutputChannel().indexOf(channel);
				break;
			default:
				System.err.println(channel.getTransType().toString() + " cannot be tranformered into a path.");
			}
		}
		return tmppath;
	}

	public static String[] partitionOutputPath(TaskStruct ts) {
		int outputNum = ts.getOutputPathNum();
		String[] tmppath = new String[outputNum];
		Vector<Channel> outputChannels = ts.getOutputChannel();
		for (int i = 0; i < outputNum; i++) {
			/*as to output path, each method is valid.*/
			switch (outputChannels.get(i).getTransType()) {
			case HDFS:
				pathPrefix = JobConfiguration.getPathHDFSPrefix();
				break;
			case HTTP:
				pathPrefix = JobConfiguration.getWorkingDirectory();
				break;
			case MSG:
				pathPrefix = JobConfiguration.getMsgHeader();
				break;
			}
			tmppath[i] = pathPrefix + "/" + ts.getTaskId() + "outputPath" + i;
		}
		return tmppath;
	}
}
