package agency.akcom.ggs.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ChatServer {
	
	private static ChatServer instance;
	private List<Message> list = new ArrayList<>();
	private int index = 0;
	private int p = 23; // Добавить их чтение из файла
	private int g = 5;
	private List<Struct> openKeys = new ArrayList<>();
	
	public class Struct {
		private double openKey;
		private String user;
		
		public Struct(String user, double openKey) {
			this.user = user;
			this.openKey = openKey;
		}
		public String getUser() { return user; }
		public double getOpenKey() { return openKey; }
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
	public int getValueP() { return this.p; }
	public int getValueG() { return this.g; }
	public void addOpenKeys(String user, double openKey) {
		this.openKeys.add(new Struct(user, openKey));
		System.out.println("open key " + openKey + " user " + user);
	}
	public double getOpenKeyCompanion(String yourName) {
		double key = 0;
		for (Struct s : openKeys){
			System.out.println(s.getUser());
			if (s.getUser() != yourName)
				key = s.getOpenKey();
		}
		return key;
	}
	
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
