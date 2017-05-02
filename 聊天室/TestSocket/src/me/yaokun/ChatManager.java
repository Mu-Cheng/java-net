package me.yaokun;

import java.util.Vector;

public class ChatManager {
	private ChatManager(){}
	private static final ChatManager cm = new ChatManager();
	public static ChatManager getChatManager(){
		return cm;
	}
	
	Vector<ChatSocket> ve = new Vector<ChatSocket>();
	public void addSocket(ChatSocket cs) {
		ve.add(cs);
	}
	
	public void publish(ChatSocket cs,String out) {
		for(int i = 0;i<ve.size();i++){
			ChatSocket csChatSocket= ve.get(i);
			if(!cs.equals(csChatSocket)){
				
				csChatSocket.out(out);
			}
		}
	}
}
