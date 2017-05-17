package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class AddUserAtRoomAction extends UnsecuredActionImpl<AddUserAtRoomResult>{
	
	private String name;
	private int room; 
	
	public AddUserAtRoomAction() {
		
	}
	public AddUserAtRoomAction(String name, int room) {
		this.name = name;
		this.room = room;
	}
	
	public String getName() { return name; }
	public int getRoom() { return room; }
}
