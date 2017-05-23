package agency.akcom.ggs.client.security;

import com.google.gwt.user.client.Cookies;

public class UserAccount {
	private static String user = "Guest";
	private static boolean flag;
	private static double openKey;
	
	public static void setUser(String userName) {
		user = userName;
		flag = true;
	}
	public static boolean loggedIn() {
		return flag;
	}
	public static void setloggediIn(boolean fl) { 
		flag = fl; 
	}
	public static String getUser() { return user; }
	public static void setKey(double key) { openKey = key; }
	public static double getKey() { return openKey; }
	public static boolean getAccess() {
		if (user.equals("Guest")){
			return false;
		} else {
			user = Cookies.getCookie("userName");
			openKey = Double.parseDouble(Cookies.getCookie("key"));
			return true;
		}
	}
}
