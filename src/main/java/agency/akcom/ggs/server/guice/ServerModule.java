package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

import agency.akcom.ggs.shared.action.FetchAdminTaskCountAction;
import agency.akcom.ggs.shared.action.GetNewMessageAction;

public class ServerModule extends HandlerModule{

	@Override
	protected void configureHandlers() {
		bindHandler(FetchAdminTaskCountAction.class, FetchAdminTaskCountHandler.class);
		bindHandler(GetNewMessageAction.class, GetNewMessageHandler.class);
	}
}
