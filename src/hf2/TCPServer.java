package hf2;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer {
	private int port;
	private ServerSocket server_socket;
	private BufferedReader input;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int port;
		ServerSocket server_socket = null;
		BufferedReader input;
		
		try {
			port = Integer.parseInt(args[0]);
		}
		catch (Exception e) {
			port = 1500;
			System.out.println("port = 1500 (default)");
		}
		
		try {
			server_socket = new ServerSocket(port);
			System.out.println("Server waiting for client. Port:" +
					server_socket.getLocalPort());
			} catch (IOException e) { 
				System.out.println(e); 
		}
		
		while (true) {
			// socket from client
			Socket socket = server_socket.accept();
			System.out.print("Socket accepted. " + socket);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter writer = new PrintWriter(new FileOutputStream("datas.txt", true));

			String inputString = input.readLine();
			writer.append("Timestamp: " + String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date().getTime())) + "\n");
			writer.append("----------------------------------\n");
			for (int i = 0; i < inputString.length(); i++) {
				if (inputString.charAt(i) == '_') {
					writer.println();
				} else {
					writer.append(inputString.charAt(i));
				}
			}
			writer.println();
			writer.close();
		}
	}
}
