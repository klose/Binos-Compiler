package com.transformer.compiler;

public class InterNodePath {
		public static String[] partitionInputPath(String taskId, int inputPathNum){
			String[] tmppath = new String[inputPathNum];
			for(int i=0;i<inputPathNum;i++){
//				tmppath[i] = System.getProperty("java.io.tmpdir")+"/"+taskId+"/inputPath/"+"inputPath"+i;
				tmppath[i] = "hdfs://10.10.102.21:26666/user/jiangbing/"+taskId+"inputPath"+i;
			}
			return tmppath;
		}
		public static String[] partitionOutputPath(String taskId, int outputPathNum){
			String[] tmppath = new String[outputPathNum];
			for(int i=0;i<outputPathNum;i++){
//				tmppath[i] = System.getProperty("java.io.tmpdir")+"/"+taskId+"/outputPath/"+"outputPath"+i;
				tmppath[i] = "hdfs://10.10.102.21:26666/user/jiangbing/"+taskId+"outputPath"+i;
			}
			return tmppath;
		}
}
