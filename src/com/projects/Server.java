package com.projects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	ServerSocket server;
	Socket socket;
	
	BufferedReader br;
	PrintWriter out;
	
	public Server() {
		
		try {
			server = new ServerSocket(9400);
			socket = server.accept();
			
			br  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			startReading();
			startWriting();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void startWriting() {
		// TODO Auto-generated method stub
		Runnable r1 = ()->{
			
			while(true) {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				try {
					String content = br.readLine();
					out.println(content);
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		new Thread(r1).start();
	}

	private void startReading() {
		// TODO Auto-generated method stub
		Runnable r1 = () -> {
			while(true) {
				try {
					String msg = br.readLine();
					if(msg == "exit") {
						break;
					}
					System.out.println("Mim: "+msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Thread(r1).start();
	}

	public static void main(String[] args) {
		
		new Server();
	}

}
