package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.UserServer;
import agency.akcom.ggs.shared.action.CheckUserExistAction;
import agency.akcom.ggs.shared.action.CheckUserExistResult;

public class CheckUserExistHandler implements ActionHandler<CheckUserExistAction, CheckUserExistResult>{

	@Override
	public CheckUserExistResult execute(CheckUserExistAction action, ExecutionContext arg1) throws ActionException {
		UserServer server = UserServer.getInstance();
		if (server.getUser(action.getName()) == true) {
			return new CheckUserExistResult(true);
		} else {
			return new CheckUserExistResult(false);
		}
	}

	@Override
	public Class<CheckUserExistAction> getActionType() {
		return CheckUserExistAction.class;
	}

	@Override
	public void undo(CheckUserExistAction arg0, CheckUserExistResult arg1, ExecutionContext arg2)
			throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
