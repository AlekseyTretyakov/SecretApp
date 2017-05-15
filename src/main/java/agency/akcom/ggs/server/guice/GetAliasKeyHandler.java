package agency.akcom.ggs.server.guice;

import javax.inject.Inject;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.ChatServer;
import agency.akcom.ggs.shared.action.GetAliasKeyAction;
import agency.akcom.ggs.shared.action.GetAliasKeyResult;

public class GetAliasKeyHandler implements ActionHandler<GetAliasKeyAction, GetAliasKeyResult>{

	@Inject 
	public GetAliasKeyHandler() {
		
	}
	@Override
	public GetAliasKeyResult execute(GetAliasKeyAction action, ExecutionContext arg1) throws ActionException {
		ChatServer server = ChatServer.getInstance();
		double key = server.getOpenKeyCompanion(action.getUserName());
		return new GetAliasKeyResult(key);
	}

	@Override
	public Class<GetAliasKeyAction> getActionType() {
		// TODO Auto-generated method stub
		return GetAliasKeyAction.class;
	}

	@Override
	public void undo(GetAliasKeyAction arg0, GetAliasKeyResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
