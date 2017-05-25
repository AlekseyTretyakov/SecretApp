package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.ChatServer;
import agency.akcom.ggs.shared.action.SendMessageAction;
import agency.akcom.ggs.shared.action.SendMessageResult;

public class SendMessageHandler implements ActionHandler<SendMessageAction, SendMessageResult>{

	@Override
	public SendMessageResult execute(SendMessageAction action, ExecutionContext arg1) throws ActionException {
		ChatServer server = ChatServer.getInstance();
		String date = server.addMsg(action.getMessage(), action.getUserName());
		return new SendMessageResult(date, server.getIndex());
	}

	@Override
	public Class<SendMessageAction> getActionType() {
		return SendMessageAction.class;
	}

	@Override
	public void undo(SendMessageAction arg0, SendMessageResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
