package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class AddUserAction extends UnsecuredActionImpl<AddUserResult>{
	
	private String name;
	private String pass;
	public AddUserAction() {}
	
	public AddUserAction(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	public String getName() { return name; }
	public String getPass() { return pass; }
}
