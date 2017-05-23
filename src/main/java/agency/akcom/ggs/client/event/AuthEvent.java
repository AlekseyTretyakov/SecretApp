package agency.akcom.ggs.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AuthEvent extends GwtEvent<AuthEventHandler>{

	public static Type<AuthEventHandler> TYPE = new Type<AuthEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AuthEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AuthEventHandler handler) {
		handler.auth(this);
	}

}
