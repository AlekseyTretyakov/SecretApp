package agency.akcom.ggs.server.guice;

import java.util.List;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.ChatServer;
import agency.akcom.ggs.shared.action.CheckCountUsersAction;
import agency.akcom.ggs.shared.action.CheckCountUsersResult;

public class CheckCountUsersHandler 
		implements ActionHandler<CheckCountUsersAction, CheckCountUsersResult>{

	@Override
	public CheckCountUsersResult execute(CheckCountUsersAction action, ExecutionContext arg1) throws ActionException {
		ChatServer server = ChatServer.getInstance();
		int count = server.getCountUsersInRoom(action.getRoom());
		return new CheckCountUsersResult(count);
	}

	@Override
	public Class<CheckCountUsersAction> getActionType() {
		return CheckCountUsersAction.class;
	}

	@Override
	public void undo(CheckCountUsersAction arg0, CheckCountUsersResult arg1, ExecutionContext arg2)
			throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
