package com.transformer.compiler;

/**
 * HTTP: task store file locally, another task fetch file through HTTP protocol.
 * HDFS: read and write data through HDFS.
 * MESSAGE: send and receive data globally through DataBus(a Master-Slave Message Service).
 * DIST_MEMORY: get and put data globally through MemDataBus.
 * CONFIG: user defined.
 * @author jiangbing
 *
 */
public enum TransmitType {
	HTTP, HDFS, MESSAGE, CONFIG, DIST_MEMORY;

//	public TransmitType parseIntoType(String type) {
//		TransmitType tType = TransmitType.valueOf(type);
//		switch(type) {
//		case
//		}
//		return null;
//		
//	}
	
}
