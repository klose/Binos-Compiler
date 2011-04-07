package com.transformer.test.compiler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import com.transformer.compiler.Channel;
import com.transformer.compiler.ChannelManager;
import com.transformer.compiler.JobCompiler;
import com.transformer.compiler.JobStruct;
import com.transformer.compiler.ParallelLevel;
import com.transformer.compiler.PhaseStruct;
import com.transformer.compiler.TaskStruct;

public class TestJob {
		public static void main(String[] args){
			JobStruct job = new JobStruct();
			ParallelLevel pal = new ParallelLevel(ParallelLevel.assignFirstLevel());
			PhaseStruct ps1 = new PhaseStruct(pal);
			PhaseStruct ps2 = new PhaseStruct(pal.currentLevel());
			PhaseStruct ps3 = new PhaseStruct(pal.nextLevel());
			PhaseStruct ps4 = new PhaseStruct(pal.assignEndLevel());
			job.addPhaseStruct(ps1);
			job.addPhaseStruct(ps2);
			job.addPhaseStruct(ps3);
			job.addPhaseStruct(ps4);
			
			TaskStruct ts1 = new TaskStruct();
			TaskStruct ts2 = new TaskStruct();
			TaskStruct ts3 = new TaskStruct();
			TaskStruct ts4 = new TaskStruct();
			
			ts1.setOperationClass(TestTaskStruct.class);
			ts2.setOperationClass(TestTaskStruct.class);
			ts3.setOperationClass(TestTaskStruct.class);
			ts4.setOperationClass(TestTaskStruct.class);
			
			String[] a = {"hdfs://10.10.102.21:26666/user/jiangbing/input","bbb","ccc","ddd"};
			String[] b = {"aaa","bbb","ccc"};
			String[] c = {"aaa"};
			ps1.addTask(ts1, 4, a);
			ps2.addTask(ts2, 3,b);
			ps3.addTask(ts3, 2);
			ps4.addTask(ts4, 1,c);	
			
			Channel[] ch1 = new Channel[ps1.getParallelNum()];
			Channel[] ch2 = new Channel[ps2.getParallelNum()];
			Channel[] ch3 = new Channel[ps3.getParallelNum()];
			
			for(int i=0;i<ps1.getParallelNum();i++)
			{	
				ch1[i] = new Channel(ps1.getTaskStruct()[i],1,ps3.getTaskStruct()[0],i);
			}
			for(int i=0;i<ps2.getParallelNum();i++)
			{	
				ch2[i] = new Channel(ps2.getTaskStruct()[i],1,ps3.getTaskStruct()[1],i);
			}
			for(int i=0;i<ps3.getParallelNum();i++)
			{	
				ch3[i] = new Channel(ps3.getTaskStruct()[i],1,ps4.getTaskStruct()[0],i);
			}
			
			ChannelManager chm = new ChannelManager();
			chm.addChannels(ch1);
			chm.addChannels(ch2);
			chm.addChannels(ch3);
			Map<String, TaskStruct> map = chm.parseDep();
			JobCompiler compiler = new JobCompiler(map, job);
			compiler.compile();
		}
}
