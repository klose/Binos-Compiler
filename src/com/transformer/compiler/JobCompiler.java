package com.transformer.compiler;

import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

public class JobCompiler {
	private Map<String, TaskStruct> taskMap; 
	private JobStruct job;
	private Map<String, String> properties = null;
	public JobCompiler(Map<String, TaskStruct> taskMap, JobStruct job, Map<String, String> jobProperties) {
		this.taskMap = taskMap;
		this.job = job;
		this.properties = jobProperties;
	}
	public JobCompiler(Map<String, TaskStruct> taskMap, JobStruct job) {
		this(taskMap, job, null);
	}
	public void compile() {
		try {
			CreateJob cj = new CreateJob(job, taskMap, properties);
			cj.generateJob();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
