package me.yaokun;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {
	private ServerSocket serverSocket;

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(54321);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println(socket);
				ChatSocket cSocket = new ChatSocket(socket);
				cSocket.start();
				ChatManager.getChatManager().addSocket(cSocket);
				String address = cSocket.socket.toString();
				
				//JOptionPane.showMessageDialog(null, address);
			}
			
			//JOptionPane.showMessageDialog(null, "�пͻ�������");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
