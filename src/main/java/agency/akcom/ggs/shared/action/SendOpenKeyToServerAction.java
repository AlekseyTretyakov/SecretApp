package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class SendOpenKeyToServerAction extends UnsecuredActionImpl<SendOpenKeyToServerResult>{
	
	private double openKey;
	private String user;
	
	public SendOpenKeyToServerAction() {}
	
	public SendOpenKeyToServerAction(String user, double openKey) {
		this.user = user;
		this.openKey = openKey;
	}
	public double getOpenKey() {
		return this.openKey;
	}
	public String getUser() {
		return this.user;
	}
}
