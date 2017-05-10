package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class FetchAdminTaskCountAction extends UnsecuredActionImpl<FetchAdminTaskCountResult> {
	private String message;
	
	public FetchAdminTaskCountAction() {
    }
	
	public FetchAdminTaskCountAction(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
}
