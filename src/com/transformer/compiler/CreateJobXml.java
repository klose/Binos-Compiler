package com.transformer.compiler;

import java.util.Map;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * @className CreateJobXml.java
 * @author Yuanzheng Lu
 * @description the program for creating a xml file which storing basic job information,including the dependence relationships between tasks;
 * @date 2011-03-21
*/
public class CreateJobXml {
	private Document document;
	private String filename;
	private JobStruct job;
	private Map<String, TaskStruct> map;
	
	/*
	 * @param  JobStruct the job which will be created xml information.
	 * @param  map the task lists corresponding to the job
	 * */
	public CreateJobXml(JobStruct job, Map<String, TaskStruct> map) throws ParserConfigurationException{
		this.map = map;
		this.job = job;
		filename=this.job.getJobXmlPath();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.newDocument();
		
	}
	
	/*
	 * @description the function is used for creating the body of job.xml file.
	 * */
	private void toWrite(){
		Element root = document.createElement("job");	
		document.appendChild(root);
		Collection<TaskStruct> collection = map.values();
		Iterator it = collection.iterator();
		
		while(it.hasNext()){
			TaskStruct taskStruct = (TaskStruct)it.next();
			
			// used for creating xml file for every task.
			try {
				CreateTaskXml ctx = new CreateTaskXml(taskStruct);
				ctx.createTaskXml();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Element task = document.createElement("task");
			task.setAttribute("id",taskStruct.getTaskId() );
			task.setAttribute("dep", String.valueOf(taskStruct.getDepNum()));
			
				
			for(int i = 0;i <taskStruct.getDepNum();i++)
			{
				Element depTask = document.createElement("taskId");
				depTask.appendChild(document.createTextNode(taskStruct.getDepId()[i]));
				task.appendChild(depTask);							
			}
			root.appendChild(task);
		}					
	
	}
	
	/*
	 * @description  save the produced file to the indicated path ,which is this.file
	 * */
	private void toSave(){
		try{
			TransformerFactory tf=TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
		}catch(TransformerException mye){
			mye.printStackTrace();
		}catch(IOException exp){
			exp.printStackTrace();
		}
	}
	
	/*
	 * @description the interface for other models 
	 * */
	public void createJobXml(){
		checkFileExist(this.job.getJobXmlPath());
		toWrite();
		toSave();			
	}
	
	public static void checkDirExist(String dirPath){
		File file = new File(dirPath);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	/*
	 * @param filePath the xml path.
	 * check the file exists or not before creating it
	 * */
	public static void checkFileExist(String filePath){
		File file = new File(filePath);
		if(!file.exists()){
//			file.mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}