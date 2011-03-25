package com.transformer.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChannelManager {
		private ArrayList<Channel> channelList = new ArrayList<Channel>();
		public ChannelManager(){}
		
		/*add channel*/
		public void addChannel(Channel channel){
			this.channelList.add(channel);
		}
		
		/*add channel array*/
		public void addChannels(Channel[] channels){
			for(int i=0;i<channels.length;i++){
				this.channelList.add(channels[i]);
			}
		}
		
		/**/
		public Map<String, TaskStruct> parseDep(){
			
			Iterator it = channelList.iterator();
			
			/*
			 * inputMap used for store the output task and its input path numbers.
			 * */
			HashMap<TaskStruct,Integer> inputMap = new HashMap<TaskStruct,Integer>();
			
			/*
			 * outputMap used for store the input task and its output path numbers.
			 * */
			HashMap<TaskStruct,Integer> outputMap = new HashMap<TaskStruct,Integer>();
			
			/*
			 * the  while used for iterating the channel list and computing final output path number.
			 * */
			while(it.hasNext()){
				Channel channel = (Channel)it.next();
				TaskStruct tsFrom = channel.getFrom();
				if(outputMap.containsKey(tsFrom)){
					int i = outputMap.get(tsFrom).intValue();
					outputMap.remove(tsFrom);
					outputMap.put(tsFrom, Integer.valueOf(i+1));
				}
				else{
					
					/*stored tsFrom's output path number*/
					outputMap.put(tsFrom, Integer.valueOf(1));
				}
			
				
				TaskStruct tsTo = channel.getTo();
				
				tsTo.addMap(tsFrom.getTaskId(), channel.getOutputIndex());
				tsTo.addDepTaskId(tsFrom.getTaskId());
				if(inputMap.containsKey(tsTo)){
					int i = inputMap.get(tsTo).intValue();
					inputMap.put(tsTo, Integer.valueOf(i+1));
				}
				else{
					/*stored tsTo's input path number*/
					inputMap.put(tsTo, Integer.valueOf(1));
				}							
			}
			
			/***************************/
	//		System.out.println(outputMap.toString());
			/***************************/
			/***************************/
//			System.out.println(inputMap.toString());
			/***************************/
			/*used for assigning inputPath and output path number for taskStructs*/
			Set<TaskStruct> inputset = inputMap.keySet();
			Set<TaskStruct> outputset = outputMap.keySet();
			Iterator inputit = inputset.iterator();
			Iterator outputit = outputset.iterator();
			Map<String, TaskStruct>  finalList = new HashMap<String, TaskStruct>();
			while(inputit.hasNext()){
				TaskStruct ints = (TaskStruct) inputit.next();
				
				if(inputMap.containsKey(ints)){
					int inputPathNum = inputMap.get(ints).intValue();
					ints.setInputPathNum(inputPathNum);
					ints.setDepNum(inputPathNum);
					String[] tmppath = new String[inputPathNum];
					tmppath = InterNodePath.partitionInputPath(ints.getTaskId(),inputPathNum);
					ints.setInputPath(tmppath);
					finalList.put(ints.getTaskId(), ints);
					
				}
				
			}
			/*
			 * add outputPath number of taskstruct which contained in the finalList.
			 * */
			while(outputit.hasNext()){
				TaskStruct outs = (TaskStruct)outputit.next();
				if(outputMap.containsKey(outs)){
					int outputPathNum = outputMap.get(outs).intValue();
					outs.setOutputPathNum(outputPathNum);
					if(finalList.containsKey(outs.getTaskId())){
						TaskStruct ts = finalList.get(outs.getTaskId());
						ts.setOutputPathNum(outputPathNum);
						String[] tmppath = new String[outputPathNum];
						tmppath = InterNodePath.partitionOutputPath(ts.getTaskId(),outputPathNum);
						ts.setOutputPath(tmppath);
						finalList.put(outs.getTaskId(), ts);
					}
					else{
						String[] tmppath = new String[outputPathNum];
						tmppath = InterNodePath.partitionOutputPath(outs.getTaskId(),outputPathNum);
						outs.setOutputPath(tmppath);
						finalList.put(outs.getTaskId(), outs);
						
					}
				}
			}
			
			
		return finalList;
		}
}
