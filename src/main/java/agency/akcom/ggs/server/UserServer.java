package agency.akcom.ggs.server;

import java.util.ArrayList;
import java.util.List;

public class UserServer {
	
	private static UserServer instance;
	private static List<User> users = new ArrayList<>();
	
	public UserServer() {}
	
	public static UserServer getInstance() {
		if (instance == null) {
			synchronized (UserServer.class) {
				if (instance == null)
					instance = new UserServer();
			}
		}
		return instance;
	}
	public void addUser(String name, String pass) {
		users.add(new User(name, pass));
		for (User user : users){
			System.out.println("New user: " + user.getName() + " - " + user.getPass());
		}
	}
	public boolean getUser(String name) {
		for (User user : users) { 
			System.out.println("name from list: " + user.getName() + " - name : " + name);
			if (user.getName().equals(name) == true) {
				System.out.println("user already exists");
				return false;
			}
		}
		return true;
	}
}
