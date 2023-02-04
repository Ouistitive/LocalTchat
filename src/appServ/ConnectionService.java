package appServ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionService implements Runnable {
	private Socket client;
	private static int nbConnection = 1;
	private int connection;
	
	public ConnectionService(Socket s) {
		client = s;
		connection = nbConnection++;
	}
	
	@Override
	public void run() {
		System.out.println("The client number " + connection + " is now connected!");
		try {
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter socketOut = new PrintWriter(client.getOutputStream(), true);
			
			String username = socketIn.readLine();
			String password = socketIn.readLine();
			
			System.out.println(username + " " + password);
			
			socketOut.println(username);
			socketOut.println(password);
			
			
			
			System.out.println("Connexion " + connection + " terminée");
			
			client.close();
		}
		catch(IOException e) {
			System.err.println(e);
		}
	}

}
