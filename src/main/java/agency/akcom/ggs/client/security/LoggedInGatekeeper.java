package agency.akcom.ggs.client.security;

import javax.inject.Inject;

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
		return currentUser.isLoggedIn();
	}

}
