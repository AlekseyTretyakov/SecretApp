package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

import agency.akcom.ggs.shared.action.FetchAdminTaskCountAction;

public class ServerModule extends HandlerModule{

	@Override
	protected void configureHandlers() {
		bindHandler(FetchAdminTaskCountAction.class, FetchAdminTaskCountHandler.class);
	}
}
