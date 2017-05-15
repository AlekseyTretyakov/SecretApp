package agency.akcom.ggs.client.security;

public class UserAccount {
	private static String user;
	private static boolean flag;
	
	public static void setUser(String userName) {
		user = userName;
		flag = true;
	}
	public static boolean loggedIn() {
		return flag;
	}
	public static String getUser() { return user; }
}
