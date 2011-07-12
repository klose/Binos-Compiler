package com.transformer.compiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * JobProperties: specify the job's property, 
 * 	and it provide a container to store some controller message.
 * Some properties: 
 * 1)the <taskId,slaveId> scheduling relation:Task is scheduled to a slave, this will record the <taskid,slaveId>
 * 2)the information about Job.   
 * @author Bing Jiang
 *
 */
public class JobProperties {
	private Map<String, String> properties = new HashMap<String, String>();
	private String jobId;
	public JobProperties() {
		
	}
	public JobProperties(String jobId) {
		this.jobId = jobId;
	}
	public void addProperty(String key, String value) {
		properties.put(key, value);
	}
	public String getProperty(String key) {
		return properties.get(key);
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	private void addAllProperties(Map<String, String> values) {
		this.properties.putAll(values);
	}
	public Map<String, String> getAllProperties() {
		return properties;
	}
	
	public void readIn(InputStream in) {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(in);
			this.setJobId((String) ois.readObject());
			Map<String, String> readProperties = (HashMap<String, String>)ois.readObject();
			addAllProperties(readProperties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public void writeOut(OutputStream out) {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(out);
			oos.writeObject(this.jobId);
			oos.writeObject(properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
