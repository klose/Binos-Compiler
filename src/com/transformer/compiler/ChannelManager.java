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
		public void addChannel(Channel channel){
			this.channelList.add(channel);
		}
		
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
					outputMap.put(tsFrom, Integer.valueOf(i+1));
				}
				else{
					/*stored tsFrom's output path number*/
					outputMap.put(tsFrom, Integer.valueOf(1));
				}
				
				TaskStruct tsTo = channel.getTo();
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
						finalList.put(outs.getTaskId(), ts);
					}
					else{
						finalList.put(outs.getTaskId(), outs);
					}
				}
			}
			
			
		return finalList;
		}
		
		public static void main(String[] args){
			/*
			 * used for test.
			 * */
			TaskStruct ts1 = new TaskStruct();
			ts1.setTaskId("1");
			TaskStruct ts2 = new TaskStruct();
			ts2.setTaskId("2");
			TaskStruct ts3 = new TaskStruct();
			ts3.setTaskId("3");
			Channel ch1 = new Channel(ts1,1,ts3,1);
			Channel ch2 = new Channel(ts2,1,ts3,2);
			ChannelManager chm = new ChannelManager();
			chm.addChannel(ch1);
			chm.addChannel(ch2);
			Map<String, TaskStruct> map = chm.parseDep();
			Set<String> set = map.keySet();
			Iterator it = set.iterator();
			while(it.hasNext()){
				TaskStruct ts = map.get((String)it.next());
				System.out.println(ts.getTaskId() + ts.getDepNum()+ ts.getInputPathNum() + ts.getOutputPathNum());
				if(ts.getDepId() != null){
					for(int i = 0;i < ts.getDepId().length;i++){
						System.out.println(ts.getDepId()[i]);
					}
				}
				
			}
			
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
}
