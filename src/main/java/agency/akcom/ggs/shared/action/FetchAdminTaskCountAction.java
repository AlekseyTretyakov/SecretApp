package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class FetchAdminTaskCountAction extends UnsecuredActionImpl<FetchAdminTaskCountResult> {
	private String message;
	private int id;
	private String user;
	
	public FetchAdminTaskCountAction() {
    }
	
	public FetchAdminTaskCountAction(String msg, String user) {
		this.message = msg;
		this.user = user;
	}
	
	public String getMessage() {
		return this.message;
	}
	public String getUser() {
		return this.user;
	}
	public int getId() {
		return this.id;
	}
}
