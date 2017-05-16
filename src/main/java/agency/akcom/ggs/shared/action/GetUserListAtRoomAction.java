package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class GetUserListAtRoomAction extends UnsecuredActionImpl<GetUserListAtRoomResult>{
	
	private int room;
	
	public GetUserListAtRoomAction() {
		
	}
	public GetUserListAtRoomAction(int room) {
		this.room = room;
	}
	public int getRoom() { return room; }
}
