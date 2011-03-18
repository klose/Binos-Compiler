package com.transformer.compiler;

public class JobStruct {
	private PhaseStruct[] ps;
	private ParallelLevel plevel;
	
	public JobStruct(ParallelLevel plevel) {
		this.plevel = plevel;
	}
	
	public void addPhaseStruct(PhaseStruct ps, ParallelLevel pl) {
		
	}
	
}
