package agency.akcom.ggs.server.guice;

import java.util.HashMap;

import javax.inject.Inject;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.ChatServer;
import agency.akcom.ggs.shared.action.FetchAdminTaskCountAction;
import agency.akcom.ggs.shared.action.FetchAdminTaskCountResult;


public class FetchAdminTaskCountHandler 
		implements ActionHandler<FetchAdminTaskCountAction, FetchAdminTaskCountResult>{
	
	@Inject
    public FetchAdminTaskCountHandler() {
    }

	@Override
	public FetchAdminTaskCountResult execute(FetchAdminTaskCountAction action, ExecutionContext arg1)
			throws ActionException {
		
		ChatServer server = ChatServer.getInstance();
		String time = server.addMsg(action.getMessage(), action.getUser());
		//return new FetchAdminTaskCountResult(action.getMessage());
		return new FetchAdminTaskCountResult(action.getMessage(), server.getIndex(), time);
	}

	@Override
	public Class<FetchAdminTaskCountAction> getActionType() {
		return FetchAdminTaskCountAction.class;
	}

	@Override
	public void undo(FetchAdminTaskCountAction arg0, FetchAdminTaskCountResult arg1, ExecutionContext arg2)
			throws ActionException {
		// TODO Auto-generated method stub
		
	}
}
