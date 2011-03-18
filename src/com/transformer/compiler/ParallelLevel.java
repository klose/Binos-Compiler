package com.transformer.compiler;

public class ParallelLevel {
	public static final int LevelFirst = 0;
	public static final int LevelEnd = -1;
	public static final int LevelMiddle = 1;
	private static int level = 0;
	public ParallelLevel() {
		this.level = 0;
	}
	public ParallelLevel(int level) {
		this.level = level;
	}
	public static ParallelLevel valueOf(int level) {
		return
	}
	public ParallelLevel assignFirstLevel() {
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
		Integer.valueOf(i);
	}
	
}
