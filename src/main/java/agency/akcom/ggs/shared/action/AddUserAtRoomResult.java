package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class AddUserAtRoomResult implements Result {
	
	private boolean addFlag;
	public AddUserAtRoomResult() {
		
	}
	public AddUserAtRoomResult(boolean flag) {
		this.addFlag = flag; 
	}
	public boolean getFlag() { return addFlag; }
}
