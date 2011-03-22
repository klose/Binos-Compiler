package com.transformer.compiler;

import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

public class CreateXml {
	private JobStruct job;
	private Map<String, TaskStruct> map;
		public CreateXml(JobStruct job, Map<String, TaskStruct> map){
			this.job = job;
			this.map = map;
		}
		public void createAll() throws ParserConfigurationException{
			CreateJobXml cjx = new CreateJobXml(job,map);
			cjx.createJobXml();
		}
}
