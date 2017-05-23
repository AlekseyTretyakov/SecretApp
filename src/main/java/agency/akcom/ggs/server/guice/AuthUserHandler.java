package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.UserServer;
import agency.akcom.ggs.shared.action.AuthUserAction;
import agency.akcom.ggs.shared.action.AuthUserResult;

public class AuthUserHandler implements ActionHandler<AuthUserAction, AuthUserResult>{

	@Override
	public AuthUserResult execute(AuthUserAction action, ExecutionContext context) throws ActionException {
		UserServer server = UserServer.getInstance();
		boolean flag = server.checkAuth(action.getUser(), action.getPass());
		if (flag == true)
		{
			return new AuthUserResult(true);
		}
		return new AuthUserResult(false);
	}

	@Override
	public Class<AuthUserAction> getActionType() {
		return AuthUserAction.class;
	}

	@Override
	public void undo(AuthUserAction action, AuthUserResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
