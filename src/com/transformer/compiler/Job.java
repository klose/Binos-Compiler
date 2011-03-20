package com.transformer.compiler;

import java.util.ArrayList;
import java.util.Iterator;

public class Job {
		private JobStruct job;
		public Job(JobStruct job){
			this.job = job;
		}
		
		/*parse the whole job struct and create job.xml, task.xml and task.jar files
		 * @param this.job
		 * */
		public void run(){
			ArrayList<PhaseStruct> phaseList = new ArrayList<PhaseStruct>();
			phaseList = this.job.getPhaseStruct();
			Iterator it = phaseList.iterator();
			
		}
}
