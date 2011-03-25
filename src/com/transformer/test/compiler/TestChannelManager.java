package com.transformer.test.compiler;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.transformer.compiler.Channel;
import com.transformer.compiler.ChannelManager;
import com.transformer.compiler.ParallelLevel;
import com.transformer.compiler.PhaseStruct;
import com.transformer.compiler.TaskStruct;

public class TestChannelManager {
	public static void main(String[] args) {
		/*
		 * used for test.
		 */
		TaskStruct ts1 = new TaskStruct();
		TaskStruct ts2 = new TaskStruct();
		TaskStruct ts3 = new TaskStruct();
		ParallelLevel pal = new ParallelLevel();
		PhaseStruct ps = new PhaseStruct(pal);
		ps.addTask(ts1, 4);
		PhaseStruct ps2 = new PhaseStruct(pal.currentLevel());
		ps2.addTask(ts2, 3);
		PhaseStruct ps3 = new PhaseStruct(pal.nextLevel());
		ps3.addTask(ts3, 1);
		Channel[] channel1 = new Channel[ps.getParallelNum()];
		Channel[] channel2 = new Channel[ps2.getParallelNum()];
		for (int i = 0; i < ps.getParallelNum(); i++) {
			channel1[i] = new Channel(ps.getTaskStruct()[i], 1,
					ps3.getTaskStruct()[0], i);
		}
		for (int i = 0; i < ps2.getParallelNum(); i++) {
			channel2[i] = new Channel(ps2.getTaskStruct()[i], 1,
					ps3.getTaskStruct()[0], ps.getParallelNum() + i);
		}

		ChannelManager chm = new ChannelManager();
		for (int i = 0; i < channel1.length; i++) {
			chm.addChannel(channel1[i]);
		}
		for (int i = 0; i < channel2.length; i++) {
			chm.addChannel(channel2[i]);
		}

		Map<String, TaskStruct> map = chm.parseDep();
		Set<String> set = map.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			TaskStruct ts = map.get((String) it.next());
			System.out.println(ts.getTaskId() + "aa" + ts.getDepNum()
					+ ts.getInputPathNum() + ts.getOutputPathNum());
			if (ts.getDepId() != null) {
				for (int i = 0; i < ts.getDepId().length; i++) {
					System.out.println(ts.getDepId()[i]);
				}
			}
			if (ts.getInputPath() != null) {
				for (int i = 0; i < ts.getInputPathNum(); i++) {
					System.out.println(ts.getInputPath()[i]);
				}
			}
			if (ts.getOutputPath() != null) {
				for (int i = 0; i < ts.getOutputPathNum(); i++) {
					System.out.println(ts.getOutputPath()[i]);
				}
			}

		}
		
	}
}
