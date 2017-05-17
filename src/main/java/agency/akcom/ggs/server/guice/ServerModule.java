package agency.akcom.ggs.server.guice;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

import agency.akcom.ggs.shared.action.AddUserAction;
import agency.akcom.ggs.shared.action.AddUserAtRoomAction;
import agency.akcom.ggs.shared.action.CheckCountUsersAction;
import agency.akcom.ggs.shared.action.CheckUserExistAction;
import agency.akcom.ggs.shared.action.FetchAdminTaskCountAction;
import agency.akcom.ggs.shared.action.GetAliasKeyAction;
import agency.akcom.ggs.shared.action.GetNewMessageAction;
import agency.akcom.ggs.shared.action.GetOpenValuesAction;
import agency.akcom.ggs.shared.action.GetUserListAtRoomAction;
import agency.akcom.ggs.shared.action.SendOpenKeyToServerAction;
import agency.akcom.ggs.shared.action.SukaAction;

public class ServerModule extends HandlerModule{

	@Override
	protected void configureHandlers() {
		bindHandler(FetchAdminTaskCountAction.class, FetchAdminTaskCountHandler.class);
		bindHandler(GetNewMessageAction.class, GetNewMessageHandler.class);
		bindHandler(GetOpenValuesAction.class, GetOpenValuesHandler.class);
		bindHandler(SukaAction.class, SukaHandler.class);
		bindHandler(GetUserListAtRoomAction.class, GetUserListAtRoomHandler.class);
		bindHandler(SendOpenKeyToServerAction.class, SendOpenKeyToServerHandler.class);
		bindHandler(GetAliasKeyAction.class, GetAliasKeyHandler.class);
		bindHandler(CheckUserExistAction.class, CheckUserExistHandler.class);
		bindHandler(AddUserAction.class, AddUserHandler.class);
		bindHandler(AddUserAtRoomAction.class, AddUserAtRoomHandler.class);
		bindHandler(CheckCountUsersAction.class, CheckCountUsersHandler.class);
	}
}
