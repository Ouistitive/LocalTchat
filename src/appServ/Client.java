package appServ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void launch(int port) {
		try {
			Socket socket = new Socket("localhost", port);
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Connected to the server port : " + port);
			
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
			
			System.out.println("Username : ");
			socketOut.print(sc.next());
			System.out.println("Password : ");
			socketOut.print(sc.next());
	
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.print("Username (recup) : ");
			System.out.println(socketIn.readLine());
			System.out.print("Password (recup) : ");
			System.out.println(socketIn.readLine());

			
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
