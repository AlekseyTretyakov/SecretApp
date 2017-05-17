package agency.akcom.ggs.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatServer {
	
	private static ChatServer instance;
	private List<Message> list = new ArrayList<>();
	//private List<SRoomUser> rooms = new ArrayList<>();
	private List<List<String>> rooms = new ArrayList<>();
	private int index = 0;
	private int p = 23; // Добавить их чтение из файла
	private int g = 5;
	private List<SKeyUser> openKeys = new ArrayList<>();
	
	public class SRoomUser {
		private int room;
		private List<String> users;
		
		public SRoomUser(String user, int room) {
			this.users.add(user);
			this.room = room;
		}
		public List<String> getUsers() { return users; }
		public double getRoom() { return room; }
	}
	public class SKeyUser {
		private double openKey;
		private String user;
		
		public SKeyUser(String user, double openKey) {
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
		this.openKeys.add(new SKeyUser(user, openKey));
		System.out.println("open key " + openKey + "; user " + user);
	}
		
	public double getOpenKeyCompanion(String yourName) {
		double key = 0;
		for (SKeyUser s : openKeys){
			//System.out.println(s.getUser());
			if (s.getUser() != yourName)
				key = s.getOpenKey();
		}
		return key;
	}
	
	public int getCountUsersInRoom(int room) {
		if (rooms.isEmpty())
			return 0;
		return rooms.get(room).size();
	}
	public List<String> getUsersInRoom(int room) {
		List<String> usr = rooms.get(room);
		return usr;
	}
 	public boolean addUserInRoom(int room, String user) {
 		List<String> usr;
 		if (rooms.isEmpty()){
 			usr = new ArrayList<>();
 			usr.add(user);
 			rooms.add(usr);
 			return true;
 		}
 		usr = rooms.get(room);
 		if (usr.size() >= 2){
 			return false;
 		}
 		usr.add(user);
		rooms.set(room, usr);
		return true;
	}
	public void removeUsersFromRoom(int room) {
		rooms.remove(room);
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
