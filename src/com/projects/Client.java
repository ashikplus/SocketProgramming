package com.projects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	Socket socket;
	
	BufferedReader br;
	PrintWriter out;
	
	public Client() {
		
		try {
			socket = new Socket("localhost",9400);
			
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
					System.out.println("Ashik: "+msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Thread(r1).start();
	}
	
	public static void main(String[] args) {
		
		new Client();
	}
}
