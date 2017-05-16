package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.ChatServer;
import agency.akcom.ggs.shared.action.AddUserAtRoomAction;
import agency.akcom.ggs.shared.action.AddUserAtRoomResult;

public class AddUserAtRoomHandler implements ActionHandler<AddUserAtRoomAction, AddUserAtRoomResult>{

	@Override
	public AddUserAtRoomResult execute(AddUserAtRoomAction action, ExecutionContext arg1) throws ActionException {
		ChatServer server = ChatServer.getInstance();
		boolean flag = server.addUserInRoom(action.getRoom(), action.getName());
		return new AddUserAtRoomResult(flag);
	}

	@Override
	public Class<AddUserAtRoomAction> getActionType() {
		return AddUserAtRoomAction.class;
	}

	@Override
	public void undo(AddUserAtRoomAction arg0, AddUserAtRoomResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
