package com.transformer.compiler;

public final class ParallelLevel {
	private static final int LevelFirst = 0;
	private static final int LevelEnd = -1;
	private static final int LevelMiddleFactor = 1;
	private final int level ;
	
	public ParallelLevel(int level) {
		this.level = level;
	}
	public ParallelLevel(ParallelLevel plevel) {
		this.level = plevel.getLevel();
	}
	private int getLevel() {
		return this.level;
	}
	public static ParallelLevel assignFirstLevel() {
		return new ParallelLevel(LevelFirst);
	}
	public static ParallelLevel assignEndLevel() {
		return new ParallelLevel(LevelEnd);
	}
	public static ParallelLevel assignCurrentLevel(ParallelLevel plevel) {
		return  new ParallelLevel(plevel);
	}	
	public ParallelLevel assignNextLevel(ParallelLevel plevel) {
		return new ParallelLevel(plevel.getLevel() + 1*LevelMiddleFactor);
	}
	
}
