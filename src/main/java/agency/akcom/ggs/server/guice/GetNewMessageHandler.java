package agency.akcom.ggs.server.guice;

import java.util.List;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import agency.akcom.ggs.server.ChatServer;
import agency.akcom.ggs.server.Message;
import agency.akcom.ggs.shared.action.GetNewMessageAction;
import agency.akcom.ggs.shared.action.GetNewMessageResult;

public class GetNewMessageHandler implements ActionHandler<GetNewMessageAction, GetNewMessageResult>{

	@Override
	public GetNewMessageResult execute(GetNewMessageAction action, ExecutionContext arg1) throws ActionException {
		ChatServer server = ChatServer.getInstance();
		if (server.CheckNewMessages(action.getIndex())){
			List<Message> result = server.getNewMessages(action.getIndex());
			String[] text = new String[result.size()];
			String[] user = new String[result.size()];
			String[] time = new String[result.size()];
			int[] index = new int[result.size()];
			System.out.println("result " + result.size());
			for (int i = 0; i < result.size(); i++){
				text[i] = result.get(i).getText();
				user[i] = result.get(i).userName();
				time[i] = result.get(i).getTime();
				index[i] = result.get(i).getId();
				System.out.println(text[i] + " " + user[i] + " " + time[i] + " " + index[i]);
			}
			
			return new GetNewMessageResult(text, index, time, user);
		}
		else return new GetNewMessageResult();
	}

	@Override
	public Class<GetNewMessageAction> getActionType() {
		return GetNewMessageAction.class;
	}

	@Override
	public void undo(GetNewMessageAction arg0, GetNewMessageResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
