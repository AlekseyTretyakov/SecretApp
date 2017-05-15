package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.shared.action.SukaAction;
import agency.akcom.ggs.shared.action.SukaResult;

public class SukaHandler 
	implements ActionHandler<SukaAction, SukaResult>{

	@Override
	public SukaResult execute(SukaAction arg0, ExecutionContext arg1) throws ActionException {
		// TODO Auto-generated method stub
		return new SukaResult();
	}

	@Override
	public Class<SukaAction> getActionType() {
		return SukaAction.class;
	}

	@Override
	public void undo(SukaAction arg0, SukaResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
