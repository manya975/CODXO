package client;

import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class ChatClient {
		private Socket s = null;
		private BufferedReader inputConsole = null;
		private PrintWriter out = null;
		private BufferedReader in = null;
		private Consumer<String> recieve;
		public ChatClient(String address,int port,Consumer<String> recieve) throws IOException{
			this.s = new Socket(address, port);
		      this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		      this.out = new PrintWriter(s.getOutputStream(), true);
		      this.recieve = recieve;
		  }

		  public void sendMessage(String msg) {
		      out.println(msg);
		  }

		  public void startClient() {
		      new Thread(() -> {
		          try {
		              String line;
		              while ((line = in.readLine()) != null) {
		                  recieve.accept(line);
		              }
		          } catch (IOException e) {
		              e.printStackTrace();
		          }
		      }).start();
		  }
}
