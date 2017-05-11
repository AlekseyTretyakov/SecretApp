package agency.akcom.ggs.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ChatServer {
	
	private static ChatServer instance;
	private List<Message> list = new ArrayList<>();
	private int index = 0;
	
	public class Struct {
		int index;
		String time;
		
		public Struct(int index, String time) {
			this.index = index;
			this.time = time;
		}
		public int getIndex() { return index; }
		public String getTime() { return time; }
	}
	
	public ChatServer() {}
	
	public static ChatServer getInstance() {
		if (instance == null) {
			synchronized (ChatServer.class) {
				if (instance == null)
					instance = new ChatServer();
			}
		}
		return instance;
	}
	public int getIndex() { return index; }
	
	public String addMsg(String text, String userName) {
		String time = new Date().toString();
		index++;
		list.add(new Message(text, index, time, userName));
		System.out.println("server " + list.size());
		return time;
	}
	public boolean CheckNewMessages(int lastId) {
		return lastId < this.index;
	}
	public List<Message> getNewMessages(int lastId) {
		List<Message> result = new ArrayList<>();
		for (int i = lastId; i < list.size(); i++) {
			result.add(list.get(i));
		}
		System.out.println("server " + list.size());
		return result;
	}
}
