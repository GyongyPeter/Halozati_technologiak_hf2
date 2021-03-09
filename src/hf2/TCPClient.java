package hf2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int port = 1500;
		String server = "localhost";
		Socket socket = null;

		// server informations
		if (args.length == 2) {
			server = args[0];
			try {
				port = Integer.parseInt(args[1]);
			} catch (Exception e) {
				System.out.println("server port = 1500 (default)");
				port = 1500;
			}
		}

		// connect to server
		try {
			socket = new Socket(server, port);
		} catch (UnknownHostException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		String lineToBeSent = "";
		BufferedReader input;
		PrintWriter writer;
		String outputString = "";
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			writer = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				lineToBeSent = input.readLine();
				if (lineToBeSent.equals(".")) {
					writer.println(outputString);
					break;
				} else {
					outputString += lineToBeSent + "_";
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		try {
			socket.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
