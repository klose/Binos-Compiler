package com.transformer.compiler;

public class ParallelLevel {
	public static final int LevelFirst = 0;
	public static final int LevelEnd = -1;
	public static final int LevelMiddle = 1;
	private int level ;
	private JobStruct js;
	public ParallelLevel(JobStruct js) {
		this.level = 0;
	}
	public int assignFirstLevel() {
		return LevelFirst;
	}
	public int assignEndLevel() {
		return LevelEnd;
	}
	public int assignCurrentLevel() {
		return this.level * LevelMiddle;
	}
	
	public int assignNextLevel() {
		return (++this.level)* LevelMiddle;
	}
	
	
}
