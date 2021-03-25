package hf2;

import java.io.IOException;
import java.net.ServerSocket;

public class UltimateServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		int port;
		ServerSocket se = null;
		
		try {
			port = Integer.parseInt(args[0]);
		}
		catch (Exception e) {
			port = 1500;
			System.out.println("port = 1500 (default)");
		}
		
		try {
			se = new ServerSocket(port);
			System.out.println("Server waiting for client. Port:" +
					se.getLocalPort());
			} catch (IOException e) { 
				System.out.println(e); 
		}

		while (true) {
			new Thread(new TCPServer(se.accept())).start();
		}
		

	}

}
