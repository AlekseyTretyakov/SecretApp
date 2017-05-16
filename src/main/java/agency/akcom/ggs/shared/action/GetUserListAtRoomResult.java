package agency.akcom.ggs.shared.action;

import java.util.ArrayList;
import java.util.List;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class GetUserListAtRoomResult implements Result{
	
	private List<String> users = new ArrayList<>();
	
	public GetUserListAtRoomResult() {}
	
	public GetUserListAtRoomResult(List<String> users) {
		this.users = users;
	}
	public List<String> getUsers() { return users; }
}
