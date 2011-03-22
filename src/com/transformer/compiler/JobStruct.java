package com.transformer.compiler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JobStruct {
	private ArrayList<PhaseStruct> phaseList = new ArrayList<PhaseStruct>();
	private String jobXmlPath;
	private static int phaseId=0;
	public JobStruct() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date curDate = new Date(System.currentTimeMillis()); // get the current time
		String str = formatter.format(curDate);
		this.jobXmlPath = System.getProperty("java.io.tmpdir") + "/"+str+".xml";
	}
	
	public void addPhaseStruct(PhaseStruct ps) {	
		ps.setPhaseID(phaseId);
		phaseList.add(ps);
		phaseId++;
	}
	public String getJobXmlPath(){
		return this.jobXmlPath;
	}
	public ArrayList<PhaseStruct> getPhaseStruct(){
		return this.phaseList;
	}
}
