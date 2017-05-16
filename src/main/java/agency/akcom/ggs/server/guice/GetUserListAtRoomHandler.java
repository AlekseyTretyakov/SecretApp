package agency.akcom.ggs.server.guice;

import java.util.List;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.ChatServer;
import agency.akcom.ggs.shared.action.GetUserListAtRoomAction;
import agency.akcom.ggs.shared.action.GetUserListAtRoomResult;

public class GetUserListAtRoomHandler 
		implements ActionHandler<GetUserListAtRoomAction, GetUserListAtRoomResult>{

	@Override
	public GetUserListAtRoomResult execute(GetUserListAtRoomAction action, ExecutionContext arg1) throws ActionException {
		ChatServer server = ChatServer.getInstance();
		
		List<String> users = server.getUsersInRoom(action.getRoom());
		
		return new GetUserListAtRoomResult(users);
	}

	@Override
	public Class<GetUserListAtRoomAction> getActionType() {
		return GetUserListAtRoomAction.class;
	}

	@Override
	public void undo(GetUserListAtRoomAction arg0, GetUserListAtRoomResult arg1, ExecutionContext arg2)
			throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
