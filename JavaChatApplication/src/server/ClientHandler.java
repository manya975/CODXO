package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable{
	private Socket clientsocket;
	private List<ClientHandler> clients;
	private PrintWriter out;
	private BufferedReader in;
	
	public ClientHandler(Socket s,List<ClientHandler> clients) throws IOException{
		this.clientsocket = s;
		this.clients = clients;
		this.out = new PrintWriter(clientsocket.getOutputStream(),true);
		this.in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
	}
	public void run() {
		try {
			String input;
			while((input = in.readLine()) != null) {
				//broadcast mssg to all
				for(ClientHandler aclient:clients) {
					aclient.out.println(input);
				}
			}
		}catch(IOException e) {
				System.out.println("Error Occured " + e.getMessage());
			}finally {
				try {
					in.close();
					out.close();
					clientsocket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}

}
