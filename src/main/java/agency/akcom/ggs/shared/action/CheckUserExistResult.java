package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class CheckUserExistResult implements Result{
	
	private boolean check;
	public CheckUserExistResult() {
		
	}
	public CheckUserExistResult(boolean check) {
		this.check = check;
	}
	public boolean getCheck() { return check; }
}
