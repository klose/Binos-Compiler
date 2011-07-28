package com.transformer.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChannelManager {
	private ArrayList<Channel> channelList = new ArrayList<Channel>();
	private ArrayList<Tunnel> tunnelList = new ArrayList<Tunnel>();
	private String pathPrefix = JobConfiguration.getPathHDFSPrefix();

	public ChannelManager() {
	}

	public void addTunnel(Tunnel tunnel) {
		tunnelList.add(tunnel);
		addChannels(tunnel.getChannel(), tunnel.getTransmitType());
	}

	// /*add channel*/
	// private void addChannel(Channel channel, TransmitType type){
	// this.channelList.add(channel);
	// }

	/* add channel array */
	private void addChannels(Channel[] channels, TransmitType type) {
		for (int i = 0; i < channels.length; i++) {
			channels[i].setTransType(type);
			this.channelList.add(channels[i]);
		}
	}

	/**/
	public Map<String, TaskStruct> parseDep() {

		Iterator it = channelList.iterator();

		/*
		 * inputMap used for store the output task and its input path numbers.
		 */
		HashMap<TaskStruct, Integer> inputMap = new HashMap<TaskStruct, Integer>();

		/*
		 * outputMap used for store the input task and its output path numbers.
		 */
		HashMap<TaskStruct, Integer> outputMap = new HashMap<TaskStruct, Integer>();

		/*
		 * the while used for iterating the channel list and computing final
		 * output path number.
		 */
		while (it.hasNext()) {
			Channel channel = (Channel) it.next();
			// System.out.println("taskStruct From" +
			// channel.getFrom().getTaskId() + " to " +
			// channel.getTo().getTaskId());
			// System.out.println(channel.toString());

			TaskStruct tsFrom = channel.getFrom();
			tsFrom.addOutputChannel(channel);
			if (outputMap.containsKey(tsFrom)) {
				int i = outputMap.get(tsFrom).intValue();
				outputMap.put(tsFrom, Integer.valueOf(i + 1));
			} else {

				/* stored tsFrom's output path number */
				outputMap.put(tsFrom, Integer.valueOf(1));
			}

			TaskStruct tsTo = channel.getTo();
			tsTo.addInputChannel(channel);
			tsTo.addMap(tsFrom.getTaskId(), channel.getOutputIndex());
			tsTo.addDepTaskId(tsFrom.getTaskId());
			if (inputMap.containsKey(tsTo)) {
				int i = inputMap.get(tsTo).intValue();
				inputMap.put(tsTo, Integer.valueOf(i + 1));
			} else {
				/* stored tsTo's input path number */
				inputMap.put(tsTo, Integer.valueOf(1));
			}
		}

		/* used for assigning inputPath and output path number for taskStructs */
		Set<TaskStruct> inputset = inputMap.keySet();
		Set<TaskStruct> outputset = outputMap.keySet();
		Iterator<TaskStruct> inputit = inputset.iterator();
		Iterator<TaskStruct> outputit = outputset.iterator();
		Map<String, TaskStruct> finalList = new HashMap<String, TaskStruct>();
		while (inputit.hasNext()) {

			TaskStruct ints = (TaskStruct) inputit.next();
			int inputPathNum = inputMap.get(ints).intValue();
			if (!ints.isConfigInput()) {
				// when job does not configure the input path, system will
				// generate them.
				ints.setInputPathNum(inputPathNum);
				ints.setDepNum(ints.getDepTaskIdList().size());
				String[] tmppath = new String[inputPathNum];
				tmppath = InterNodePath.partitionInputPath(ints);
				ints.setInputPath(tmppath);
			}
			// else {
			//
			// }
			//
			// pathPrefix = JobConfiguration.getPathHDFSPrefix();
			// if (ints.getDepTaskMap() == null) {
			// //this is used to add depende
			//
			// String[] s = new String[ints.getDepTaskMap().size()];
			// Set<String> set = ints.getDepTaskMap().keySet();
			// Iterator ite = set.iterator();
			// int i = 0;
			// while (ite.hasNext()) {
			// String key = (String) ite.next();
			// s[i] = pathPrefix + "/" + key.trim() + "outputPath"
			// + (ints.getDepTaskMap().get(key)).intValue();
			// i++;
			// }
			// ints.setInputPath(s);
			// }
			finalList.put(ints.getTaskId(), ints);

		}
		/*
		 * add outputPath number of taskstruct which contained in the finalList.
		 */
		while (outputit.hasNext()) {
			TaskStruct outs = (TaskStruct) outputit.next();
			int outputPathNum = outputMap.get(outs).intValue();
			outs.setOutputPathNum(outputPathNum);
			if (finalList.containsKey(outs.getTaskId())) {
				TaskStruct ts = finalList.get(outs.getTaskId());
				ts.setOutputPathNum(outputPathNum);
				if (!outs.isConfigOutput()) {
					String[] tmppath = new String[outputPathNum];
					tmppath = InterNodePath.partitionOutputPath(ts);
					ts.setOutputPath(tmppath);
				}
				// pathPrefix = JobConfiguration.getPathHDFSPrefix();
				// if(ts.getDepTaskMap() != null){
				// String[] s = new String[ts.getDepTaskMap().size()];
				// System.out.println("" + s.length);
				// Set<String> set = ts.getDepTaskMap().keySet();
				// Iterator ite = set.iterator();
				// int i = 0;
				// while(ite.hasNext()){
				// String key = (String)ite.next();
				// s[i] = pathPrefix + "/" + key.trim()
				// +"outputPath"+(ts.getDepTaskMap().get(key)).intValue();
				// i++;
				// }
				// ts.setInputPath(s);
				// }
				finalList.put(outs.getTaskId(), ts);
			} else {
				if (!outs.isConfigOutput()) {
					String[] tmppath = new String[outputPathNum];
					tmppath = InterNodePath.partitionOutputPath(outs);
					outs.setOutputPath(tmppath);
				}
				finalList.put(outs.getTaskId(), outs);

			}

		}

		return finalList;
	}
}
