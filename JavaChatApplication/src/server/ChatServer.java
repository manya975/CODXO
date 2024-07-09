package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	private static List<ClientHandler> clients = new ArrayList<>(); //to handle multiple client connection requests
	public static void main(String[] args) throws IOException{
		ServerSocket serversocket = new ServerSocket(5000);
		System.out.println("Server started,,Waiting for clients..");
		while(true) {
			Socket clientsocket = serversocket.accept();
		System.out.println("Client connected" + clientsocket);
		//new thread for each client
		ClientHandler c = new ClientHandler(clientsocket,clients);
		clients.add(c);
		new Thread(c).start();
		}
	}
}

