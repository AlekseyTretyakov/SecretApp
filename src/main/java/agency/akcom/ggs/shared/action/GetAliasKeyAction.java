package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class GetAliasKeyAction extends UnsecuredActionImpl<GetAliasKeyResult>{
	
	private String userName; 
	
	public GetAliasKeyAction()	{
		
	}
	public GetAliasKeyAction(String userName) {
		this.userName = userName;
	}
	public String getUserName() { return userName; }
}
