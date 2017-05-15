package agency.akcom.ggs.client.application.checkin;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

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
import agency.akcom.ggs.client.security.CurrentUser;
import agency.akcom.ggs.client.security.UserAccount;
import agency.akcom.ggs.shared.action.AddUserAction;
import agency.akcom.ggs.shared.action.AddUserResult;
import agency.akcom.ggs.shared.action.CheckUserExistAction;
import agency.akcom.ggs.shared.action.CheckUserExistResult;
import agency.akcom.ggs.shared.action.GetOpenValuesAction;
import agency.akcom.ggs.shared.action.GetOpenValuesResult;


public class CheckinPresenter extends Presenter<CheckinPresenter.MyView, CheckinPresenter.MyProxy>
	implements CheckinUiHandlers {
	
	@ProxyStandard
	@NameToken(NameTokens.CHECKIN)
    @NoGatekeeper
	interface MyProxy extends ProxyPlace<CheckinPresenter> {
    }

    interface MyView extends View, HasUiHandlers<CheckinUiHandlers> {
    	void checkUserName(boolean check);
    }
    
    private final CurrentUser currentUser;
    private final DispatchAsync dispatcher;	
    private final PlaceManager	placeManager;
    Logger logger = Logger.getLogger("TestLogger");
    
    @Inject
    CheckinPresenter(EventBus eventBus,
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
	public void onRegUser(final String name, String pass) {
		
		
		dispatcher.execute(new AddUserAction(name, pass), new AsyncCallback<AddUserResult>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert(arg0 + "");				
			}

			@Override
			public void onSuccess(AddUserResult arg0) {
				currentUser.setUser(name);
				currentUser.setLoggedIn(true);
				logger.log(Level.INFO, "name " + name);
				UserAccount.setUser(name);
				
				goToChatPage();
		}});
	}

	@Override
	public void onCheckUserName(String name) {
		CheckUserExistAction action2 = new CheckUserExistAction(name);
		
		dispatcher.execute(action2, new AsyncCallback<CheckUserExistResult>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught + "");
			}

			@Override
			public void onSuccess(CheckUserExistResult result) {
				logger.log(Level.INFO, "check: " + result.getCheck());
				getView().checkUserName(result.getCheck());
		}});
	}
	public void goToChatPage() {
		
		PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.CHAT)
                .build();
        placeManager.revealPlace(placeRequest);
	}
}
