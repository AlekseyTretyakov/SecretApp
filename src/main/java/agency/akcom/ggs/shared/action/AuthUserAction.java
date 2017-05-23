package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class AuthUserAction extends UnsecuredActionImpl<AuthUserResult>{
	
	private String user;
	private String pass;
	
	public AuthUserAction() {
		
	}
	public AuthUserAction(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	public String getUser() { return user; }
	public String getPass() { return pass; }
}
