package com.themt.mtchat.server;

import java.net.InetAddress;

public class ServerClient {
	
	
	public String name;
	public InetAddress address;
	/*192.168.0.10 //ID : 19216801025443*/
	public int port;
	private final int ID;
	public int attempt = 0;
	
	public ServerClient(String name, InetAddress address, int port, final int ID) {
		this.address = address;
		this.port = port;
		this.name = name;
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
}
