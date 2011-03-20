package com.transformer.compiler;

import java.util.ArrayList;

public class JobStruct {
	private ArrayList<PhaseStruct> phaseList = new ArrayList<PhaseStruct>();
	private String jobId;
	private String jobXmlPath;
	public JobStruct(String jobId) {
		this.jobId = jobId;
		this.jobXmlPath = System.getProperty("java.io.tmpdir") + "/"+this.jobId+"/"+this.jobId+".xml";
	}
	
	public void addPhaseStruct(PhaseStruct ps) {	
		phaseList.add(ps);
	}
	public String getJobXmlPath(){
		return this.jobXmlPath;
	}
	public ArrayList<PhaseStruct> getPhaseStruct(){
		return this.phaseList;
	}
}
