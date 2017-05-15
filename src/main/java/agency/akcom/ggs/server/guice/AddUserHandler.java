package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.UserServer;
import agency.akcom.ggs.shared.action.AddUserAction;
import agency.akcom.ggs.shared.action.AddUserResult;

public class AddUserHandler implements ActionHandler<AddUserAction, AddUserResult>{

	@Override
	public AddUserResult execute(AddUserAction action, ExecutionContext arg1) throws ActionException {
		UserServer server = UserServer.getInstance();
		server.addUser(action.getName(), action.getPass());
		return new AddUserResult();
	}

	@Override
	public Class<AddUserAction> getActionType() {
		return AddUserAction.class;
	}

	@Override
	public void undo(AddUserAction arg0, AddUserResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
