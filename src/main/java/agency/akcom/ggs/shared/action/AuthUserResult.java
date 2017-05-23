package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class AuthUserResult implements Result{
	
	private boolean auth;
	
	public AuthUserResult() {
		auth = false;
	}
	public AuthUserResult(boolean auth) {
		this.auth = auth;
	}
	public boolean getAuth() { return auth; }
}
