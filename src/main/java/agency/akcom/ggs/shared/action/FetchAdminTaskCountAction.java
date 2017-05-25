package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

import agency.akcom.ggs.shared.Message;

public class FetchAdminTaskCountAction extends UnsecuredActionImpl<FetchAdminTaskCountResult> {
	private String message;
	private int id;
	private String user;
	private Message msg;
	
	public FetchAdminTaskCountAction() {
    }
	
	public FetchAdminTaskCountAction(String msg, String user) {
		this.message = msg;
		this.user = user;
	}
	public FetchAdminTaskCountAction(Message msg){
		this.msg = msg;
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
	public Message getMsg() { 
		return this.msg; 
	}
}
