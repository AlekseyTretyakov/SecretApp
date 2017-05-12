package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.ChatServer;
import agency.akcom.ggs.shared.action.GetOpenValuesAction;
import agency.akcom.ggs.shared.action.GetOpenValuesResult;

public class GetOpenValuesHandler implements ActionHandler<GetOpenValuesAction, GetOpenValuesResult>{

	@Override
	public GetOpenValuesResult execute(GetOpenValuesAction action, ExecutionContext arg1) throws ActionException {
		ChatServer server = ChatServer.getInstance();
		int p = server.getValueP();
		int g = server.getValueG();
		return new GetOpenValuesResult(p, g);
	}

	@Override
	public Class<GetOpenValuesAction> getActionType() {
		return GetOpenValuesAction.class;
	}

	@Override
	public void undo(GetOpenValuesAction arg0, GetOpenValuesResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
