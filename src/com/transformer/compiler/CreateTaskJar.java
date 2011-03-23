package com.transformer.compiler;

public class CreateTaskJar {
	private TaskStruct taskStruct;
	public CreateTaskJar(TaskStruct taskStruct) {
		this.taskStruct = taskStruct;
	}
	public void createTaskJar() {
		JarFile jar = new JarFile();
		taskStruct.getTaskJarPath();
	}
}
