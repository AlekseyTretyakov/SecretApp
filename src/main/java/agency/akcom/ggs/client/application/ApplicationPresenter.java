package agency.akcom.ggs.client.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import agency.akcom.ggs.client.NameTokens;
import agency.akcom.ggs.client.application.ApplicationPresenter.MyProxy;
import agency.akcom.ggs.client.application.ApplicationPresenter.MyView;
import agency.akcom.ggs.client.event.AuthEvent;
import agency.akcom.ggs.client.event.AuthEventHandler;
import agency.akcom.ggs.client.security.IsCustomGatekeeper;
import agency.akcom.ggs.client.security.UserAccount;

public class ApplicationPresenter extends Presenter<MyView, MyProxy> implements ApplicationUiHandlers{
    interface MyView extends View, HasUiHandlers<ApplicationUiHandlers>{
    	void setUserName(String name);
    }

    @ProxyStandard
    interface MyProxy extends Proxy<ApplicationPresenter> {
    }
    
    private PlaceManager placeManager;
    private final EventBus eventBus;
    private final IsCustomGatekeeper gatekeeper;
    private Logger logger = Logger.getLogger("logger");
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<>();
    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    @Inject
    ApplicationPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager,
            IsCustomGatekeeper gatekeeper) {
        super(eventBus, view, proxy, RevealType.Root);
        
        this.eventBus = eventBus;
        this.placeManager = placeManager;
        this.gatekeeper = gatekeeper;
        eventsAuth();
        //tryAuth();
    	getView().setUiHandlers(this);
    }
    public void eventsAuth() {
    	logger.log(Level.INFO, "events auth  func");
    	eventBus.addHandler(AuthEvent.TYPE, new AuthEventHandler(){

			@Override
			public void auth(AuthEvent event) {
				logger.log(Level.INFO, "event auth fire");
				getView().setUserName(UserAccount.getUser());
			}});
    }
	@Override
	public void onLogin() {
		PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.LOGIN)
                .build();
        placeManager.revealPlace(request);
	}
	@Override
	public void onCheckin() {
		PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.CHECKIN)
                .build();
        placeManager.revealPlace(request);
	}
	@Override
	public void onLogout() {
		//TODO UserAccount.clear & Cookies.clear
		UserAccount.setKey(0);
		UserAccount.setUser("Guest");
		UserAccount.setloggediIn(false);
		Cookies.removeCookie("userName");
		Cookies.removeCookie("key");
		PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.LOGIN)
                .build();
        placeManager.revealPlace(request);
	}
	public void tryAuth() {
		String userName = Cookies.getCookie("userName");
		if (userName != null){
			UserAccount.setUser(userName);
			UserAccount.setloggediIn(true);
			eventBus.fireEvent(new AuthEvent());
			Window.alert("eee");
		}
	}
}
