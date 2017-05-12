package agency.akcom.ggs.client.security;

public class CurrentUser {
	private Boolean loggedIn;
	private String user;
	
	public CurrentUser() {
        loggedIn = false;
    }
	
	public Boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public String getUser() {
    	return user;
    }
    public void setUser(String user){
    	this.user = user;
    }
}
