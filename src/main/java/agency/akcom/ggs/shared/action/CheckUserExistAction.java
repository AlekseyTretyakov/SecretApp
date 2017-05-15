package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class CheckUserExistAction extends UnsecuredActionImpl<CheckUserExistResult>{
	
	private String name; 
	
	public CheckUserExistAction() {}
	
	public CheckUserExistAction(String name) {
		this.name = name;
	}
	public String getName() { return name; }
}
