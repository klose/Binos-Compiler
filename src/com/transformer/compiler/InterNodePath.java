package com.transformer.compiler;

public class InterNodePath {
		public static String[] partitionInputPath(String taskId, int inputPathNum){
			String[] tmppath = new String[inputPathNum];
			for(int i=0;i<inputPathNum;i++){
				tmppath[i] = System.getProperty("java.io.tmpdir")+"/"+taskId+"/inputPath/"+"inputPath"+i;
			}
			return tmppath;
		}
		public static String[] partitionOutputPath(String taskId, int outputPathNum){
			String[] tmppath = new String[outputPathNum];
			for(int i=0;i<outputPathNum;i++){
				tmppath[i] = System.getProperty("java.io.tmpdir")+"/"+taskId+"/outputPath/"+"outputPath"+i;
			}
			return tmppath;
		}
}
