package agency.akcom.ggs.client.application.login;

import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import agency.akcom.ggs.client.NameTokens;
import agency.akcom.ggs.client.application.ApplicationPresenter;
import agency.akcom.ggs.client.security.CurrentUser;
import agency.akcom.ggs.client.security.UserAccount;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
		implements LoginUiHandlers {
	@ProxyStandard
    @NameToken(NameTokens.LOGIN)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
    }
    
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private final CurrentUser currentUser;
    private final DispatchAsync dispatcher;	
    private final PlaceManager	placeManager;
    
    @Inject
    LoginPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            CurrentUser currentUser,
            DispatchAsync dispatcher,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.currentUser = currentUser;
        this.dispatcher = dispatcher;
        this.placeManager = placeManager;
        getView().setUiHandlers(this);
    }

	@Override
	public void onConfirm(String userName, String userPass) {
		if (validateCredentials(userName, userPass)) {
			currentUser.setUser(userName);
            currentUser.setLoggedIn(true);
            
            UserAccount.setUser(userName);
            
            PlaceRequest placeRequest = new PlaceRequest.Builder()
                    .nameToken(NameTokens.CHAT)
                    .build();
            placeManager.revealPlace(placeRequest);
        }
	}
	private boolean validateCredentials(String username, String password) {
		return true;
        //return username.equals(USERNAME) && password.equals(PASSWORD);
    }
}
