package appServ;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Runnable {
	private int port;
	private ServerSocket server;
	
	public Server(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(this.port);
	}
	
	@Override
	public void run() {
		InetAddress myIp = null;
		try {
			myIp = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("The server is launched on the adress " + myIp.getHostAddress() + ":" + port);
		
		try {
			while(true) {
				Socket clientSocket = server.accept();
				new Thread(new ConnectionService(clientSocket)).start();
			}
		}
		catch(IOException e) {
			System.err.println(e);
		}
	}

}
