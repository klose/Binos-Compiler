package com.transformer.compiler;
/**
 * Tunnel:make connection from one PhaseStruct to another PhaseStruct.
 * It can configure about the channels from one PhaseStruct to another PhaseStrut.  
 * @author jiangbing
 *
 */
public class Tunnel {
	private PhaseStruct fromPh;
	private PhaseStruct toPh;
	private Channel[] channel;
	private final TransmitType transmitType;
	public Tunnel(TransmitType transmitType){
		this.transmitType = transmitType;
	}
	public Tunnel(PhaseStruct from, PhaseStruct to, TransmitType transmitType) {
		this(transmitType);
		this.fromPh = from;
		this.toPh = to;
	}
	public Tunnel(Channel[] channel, TransmitType transmitType) {
		this(transmitType);
		this.channel = channel;
	}
	public Tunnel(PhaseStruct from, PhaseStruct to, Channel[] channel, TransmitType transmitType){
		this(from, to, transmitType);
		this.channel = channel;
	}
	public PhaseStruct getFromPh() {
		return fromPh;
	}
	public void setFromPh(PhaseStruct fromPh) {
		this.fromPh = fromPh;
	}
	public PhaseStruct getToPh() {
		return toPh;
	}
	public void setToPh(PhaseStruct toPh) {
		this.toPh = toPh;
	}
	public Channel[] getChannel() {
		return channel;
	}
	public void setChannel(Channel[] channel) {
		this.channel = channel;
	}
	public TransmitType getTransmitType() {
		return transmitType;
	}
}
