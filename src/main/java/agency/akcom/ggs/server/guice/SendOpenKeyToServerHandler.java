package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.ChatServer;
import agency.akcom.ggs.shared.action.SendOpenKeyToServerAction;
import agency.akcom.ggs.shared.action.SendOpenKeyToServerResult;

public class SendOpenKeyToServerHandler implements ActionHandler<SendOpenKeyToServerAction, SendOpenKeyToServerResult>{

	@Override
	public SendOpenKeyToServerResult execute(SendOpenKeyToServerAction action, ExecutionContext arg1)
			throws ActionException {
		System.out.println(action.getOpenKey() + " " + action.getUser());
		ChatServer server = ChatServer.getInstance();
		server.addOpenKeys(action.getUser(), action.getOpenKey());
		/*
		 * Сделать чтобы тут возвращался открытый ключ чужой если есть
		 */
		return new SendOpenKeyToServerResult();
	}

	@Override
	public Class<SendOpenKeyToServerAction> getActionType() {
		// TODO Auto-generated method stub
		return SendOpenKeyToServerAction.class;
	}

	@Override
	public void undo(SendOpenKeyToServerAction arg0, SendOpenKeyToServerResult arg1, ExecutionContext arg2)
			throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
