package agency.akcom.ggs.client.security;

import javax.inject.Inject;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

import agency.akcom.ggs.client.event.AuthEvent;

abstract public class IsLoggedInGatekeeper implements Gatekeeper{

	private final EventBus eventBus;
	
	@Inject
	IsLoggedInGatekeeper(EventBus eventBus){
		this.eventBus = eventBus;
	}
	
	@Override
	public boolean canReveal() {
		String userName = Cookies.getCookie("userName");
		if (userName == null){
			Window.alert("null");
		} else {
			UserAccount.setUser(userName);
			UserAccount.setloggediIn(true);
			eventBus.fireEvent(new AuthEvent());
		}
		return UserAccount.loggedIn();
	}

}
