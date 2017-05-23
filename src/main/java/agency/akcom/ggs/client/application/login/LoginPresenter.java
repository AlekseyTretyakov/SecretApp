package agency.akcom.ggs.client.application.login;

import javax.inject.Inject;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import agency.akcom.ggs.client.event.AuthEvent;
import agency.akcom.ggs.client.security.CurrentUser;
import agency.akcom.ggs.client.security.UserAccount;
import agency.akcom.ggs.shared.action.AuthUserAction;
import agency.akcom.ggs.shared.action.AuthUserResult;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
		implements LoginUiHandlers {
	@ProxyStandard
    @NameToken(NameTokens.LOGIN)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
    	void setWarInputs(boolean auth);
    }
    
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private final CurrentUser currentUser;
    private final DispatchAsync dispatcher;	
    private final PlaceManager	placeManager;
    private final EventBus eventBus;
    
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
        this.eventBus = eventBus;
        getView().setUiHandlers(this);
    }

	@Override
	public void onConfirm(final String userName, String userPass) {
		
		dispatcher.execute(new AuthUserAction(userName, userPass), new AsyncCallback<AuthUserResult>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught + "");
			}


			@Override
			public void onSuccess(AuthUserResult result) {
				getView().setWarInputs(result.getAuth());
				UserAccount.setUser(userName);
				UserAccount.setloggediIn(true);
				currentUser.setUser(userName);
				currentUser.setLoggedIn(true);
				Cookies.setCookie("userName", userName);
				
				eventBus.fireEvent(new AuthEvent()); 
				
				PlaceRequest placeRequest = new PlaceRequest.Builder()
	                    .nameToken(NameTokens.HOME)
	                    .build();
	            placeManager.revealPlace(placeRequest);
			}
			
		});
		
		
	}
}
