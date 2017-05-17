package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class CheckCountUsersResult implements Result{
	
	private int count;
	
	public CheckCountUsersResult() {
		
	}
	public CheckCountUsersResult(int count) {
		this.count = count;
	}
	public int getUsersCount() { return count; }
}
