package com.transformer.compiler;

public class PartitonPath {
	protected static String[] seqPart(int parallelNum,String[] path,int i){
		int k = path.length/parallelNum;
		String[] tmppath = new String[k];
		for(int j=0;j<k;j++){
			tmppath[j] = path[i*parallelNum+j];
		}
		return tmppath;
	}
}
