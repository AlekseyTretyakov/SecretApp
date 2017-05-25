package agency.akcom.ggs.client.security;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;

@DefaultGatekeeper
public class IsCustomGatekeeper extends IsLoggedInGatekeeper{

	@Inject
	public IsCustomGatekeeper(EventBus eventBus) {
		super(eventBus);
	}
	public boolean canReveal() {
		return super.canReveal();
	}
}
