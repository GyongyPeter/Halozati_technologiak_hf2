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

public class TCPServer implements Runnable {
	private Socket s;
	ServerSocket server_socket = null;
	BufferedReader input;

	public TCPServer(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		System.out.print("Socket accepted. " + this.s);
		try {
			input = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			PrintWriter writer;
			writer = new PrintWriter(new FileOutputStream("datas.txt", true));

			String inputString = input.readLine();
			writer.append("Timestamp: "
					+ String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date().getTime()))
					+ "\n");
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
