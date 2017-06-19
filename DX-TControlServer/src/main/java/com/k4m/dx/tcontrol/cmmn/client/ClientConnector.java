package com.k4m.dx.tcontrol.cmmn.client;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnector extends ClientSocketCtl{	
	public void create(String ipaddr, int port) throws UnknownHostException, ConnectException, IOException {		
		this.ipaddr		= ipaddr;
		this.port		= port;
		
		if (client == null) {
			client	= new Socket(this.ipaddr, this.port);
			
			client.setSoTimeout(timeout * 1000);
			
			os		= client.getOutputStream();
			is		= client.getInputStream();
		}
	}
	
	public void create(String ipaddr, int port, Object caller) throws UnknownHostException, ConnectException, IOException {
		create(ipaddr, port);
		
		if (caller != null) {
			this._caller	= caller.getClass().getName();
		}
	}
	
	public void create(Socket s) throws IOException {
		client	= s;
		
		client.setSoTimeout(timeout * 1000);
		
		os		= client.getOutputStream();
		is		= client.getInputStream();
	}
		
	public void close() {
		try {
			if (is != null) { is.close(); is = null; }
			if (os != null) { os.close(); os = null; }
			if (client != null) { client.close(); client = null; }
		} catch (Exception e) {
		}
	}
	
	public void finalize() {
		if (client != null) {
			close();
        }
	}	
	
	public ClientConnector() {
	}
}