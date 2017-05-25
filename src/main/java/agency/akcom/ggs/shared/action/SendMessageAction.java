package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class SendMessageAction extends UnsecuredActionImpl<SendMessageResult>{
	
	private String message;
	private String user;
	
	public SendMessageAction() {}
	
	public SendMessageAction(String message, String user) {
		this.message = message;
		this.user = user;
	}
	public String getMessage() { return message; }
	public String getUserName() { return user; }
}
