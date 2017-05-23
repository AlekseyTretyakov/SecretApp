package agency.akcom.ggs.client.security;

import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;


@DefaultGatekeeper
public class LoggedInGatekeeper implements Gatekeeper{
	private CurrentUser currentUser;
	
	
	@Inject
	LoggedInGatekeeper(CurrentUser currentUser){
		this.currentUser = currentUser;
	}
	@Override
	public boolean canReveal() {
		//Window.alert(UserAccount.getUser());
		return UserAccount.loggedIn();
		//return currentUser.isLoggedIn();
	}

}
