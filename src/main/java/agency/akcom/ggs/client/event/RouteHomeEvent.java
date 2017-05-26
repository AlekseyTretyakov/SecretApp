package agency.akcom.ggs.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RouteHomeEvent extends GwtEvent<RouteHomeHandler>{
	
	public static Type<RouteHomeHandler> TYPE = new Type<>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RouteHomeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RouteHomeHandler handler) {
		handler.route(this);
	}

}
