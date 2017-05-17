package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class CheckCountUsersAction extends UnsecuredActionImpl<CheckCountUsersResult>{
	
	private int room;
	
	public CheckCountUsersAction() {
		
	}
	public CheckCountUsersAction(int room) {
		this.room = room;
	}
	public int getRoom() { return room; }
}
