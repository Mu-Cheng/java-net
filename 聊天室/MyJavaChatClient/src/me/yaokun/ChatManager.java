package me.yaokun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import me.yaokun.view.MainWindow;

public class ChatManager {
	private ChatManager() {}
	
	private static final ChatManager instance = new ChatManager();
	
	public static ChatManager getCM() {
		return instance;
	}
	
	private MainWindow window;
	
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	
	public MainWindow getWindow() {
		return window;
	}

	public void setWindow(MainWindow window) {
		this.window = window;
		window.appendText("�ı����Ѿ�����");
	}

	public void connect(String ip) {
		new Thread(){

			@Override
			public void run() {
				try {
					socket = new Socket(ip, 54321);
					writer = new PrintWriter(
							new OutputStreamWriter(
									socket.getOutputStream()));
					reader = new BufferedReader(
							new InputStreamReader(
									socket.getInputStream()));
					
					String line;
					while ((line = reader.readLine())!=null) {
						window.appendText("�յ���"+line);
						
					}
					
					writer.close();
					reader.close();
					writer = null;
					reader = null;
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}.start();
	}

	public void send(String out) {
		if(writer!=null){
			writer.write(out+"\n");
			writer.flush();
		}else {
			window.appendText("��ǰ�������ж�");
		}
		
	}
}
