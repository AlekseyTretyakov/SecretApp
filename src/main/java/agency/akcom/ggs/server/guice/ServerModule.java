package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

import agency.akcom.ggs.shared.action.FetchAdminTaskCountAction;
import agency.akcom.ggs.shared.action.GetNewMessageAction;
import agency.akcom.ggs.shared.action.GetOpenValuesAction;
import agency.akcom.ggs.shared.action.SendOpenKeyToServerAction;

public class ServerModule extends HandlerModule{

	@Override
	protected void configureHandlers() {
		bindHandler(FetchAdminTaskCountAction.class, FetchAdminTaskCountHandler.class);
		bindHandler(GetNewMessageAction.class, GetNewMessageHandler.class);
		bindHandler(GetOpenValuesAction.class, GetOpenValuesHandler.class);
		bindHandler(SendOpenKeyToServerAction.class, SendOpenKeyToServerHandler.class);
	}
}
