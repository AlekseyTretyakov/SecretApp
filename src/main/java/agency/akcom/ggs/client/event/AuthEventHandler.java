package agency.akcom.ggs.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AuthEventHandler extends EventHandler{
	void auth(AuthEvent event);
}
