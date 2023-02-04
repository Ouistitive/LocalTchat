package appli;

import java.io.IOException;
import java.util.Scanner;

import appServ.Client;
import appServ.Server;
import database.JDBC;

public class Appli {
	private static Scanner sc;
	private static final int MIN_PORT = 4000;
	private static int PORT;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		PORT = choicePort();

		try {
			if(choiceServerOrClient() == Role.SERVER) {
				new Thread(new Server(PORT)).start();
			}
			else {
				Client.launch(PORT);
			}	
		}
		catch(IOException e) {
			System.err.println(e);
		}
		
		
		
		/*JDBC.connection();
		
		JDBC.sendQuery("SELECT * FROM User");
		
		JDBC.closeDatabase();*/
		
		sc.close();
	}
	
	/**
	 * @brief The user choose if he want to be the host or the client
	 * - The host can only read message and see the connections
	 * - The client can read, write message on the chat after the connection
	 * @return the role of the user
	 */
	public static Role choiceServerOrClient() {
		Role choice = Role.NULL;
		do {
			System.out.println("Would you be the host or the client?");
			System.out.println("1. Host\n2. Client");
				
			try {
				choice = Role.get(Integer.parseInt(sc.next()));
			}
			catch(NumberFormatException e) {
				choice = Role.NULL;
			}
			
		} while (choice == Role.NULL);
		
		return choice;
	}
	
	public static int choicePort() {
		int port = 0;
		do {
			System.out.println("Which port would you use? (" + MIN_PORT +"<)");
			
			try {
				port = Integer.parseInt(sc.next());
			}
			catch(NumberFormatException e) {
				port = 0;
			}
		} while(port < MIN_PORT);
		return port;
	}
}
