package agency.akcom.ggs.client.application.home;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
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
import agency.akcom.ggs.client.ParametrTokens;
import agency.akcom.ggs.client.application.ApplicationPresenter;
import agency.akcom.ggs.client.event.RouteHomeEvent;
import agency.akcom.ggs.client.event.RouteHomeHandler;
import agency.akcom.ggs.client.security.UserAccount;
import agency.akcom.ggs.shared.action.AddUserAtRoomAction;
import agency.akcom.ggs.shared.action.AddUserAtRoomResult;
import agency.akcom.ggs.shared.action.CheckCountUsersAction;
import agency.akcom.ggs.shared.action.CheckCountUsersResult;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy>
		implements HomeUiHandlers {
    interface MyView extends View, HasUiHandlers<HomeUiHandlers> {
    	void setButtonEnabled(int num, boolean flag);
    }

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }
    
    Logger logger = Logger.getLogger("logger");
    private final EventBus eventBus;
    private final PlaceManager	placeManager;
    private final DispatchAsync dispatcher;
    @Inject
    HomePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager,
            DispatchAsync dispathcer) {
    	
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        this.placeManager = placeManager;
        this.dispatcher = dispathcer;
        this.eventBus = eventBus;
        getView().setUiHandlers(this);
        onRouteHome();
        checkFreeChannels();
    }
    
    public void onRouteHome() {
    	eventBus.addHandler(RouteHomeEvent.TYPE, new RouteHomeHandler(){

			@Override
			public void route(RouteHomeEvent event) {
				//Window.alert("welcome to home!");
				Timer t = new Timer(){

					@Override
					public void run() {
						checkFreeChannels();
					}
		        	
		        };
		        t.scheduleRepeating(2000);
			}});
    }
    
    public void checkFreeChannels() {
    	dispatcher.execute(new CheckCountUsersAction(0), new AsyncCallback<CheckCountUsersResult>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught + "");
			}

			@Override
			public void onSuccess(CheckCountUsersResult result) {
				if (result.getUsersCount() < 2)
					getView().setButtonEnabled(0, true);
				else {
					if (UserAccount.getChannel() == 0){
						getView().setButtonEnabled(0, true);
					} else getView().setButtonEnabled(0, false);
				}
				//Window.alert("food" + result.getUsersCount());
			}});
    }
    
	@Override
	public void onChooseChanel1(final int ch) {
		if (UserAccount.getChannel() == ch){
			PlaceRequest placeRequest = new PlaceRequest.Builder()
	                .nameToken(NameTokens.CHAT)
	                .with(ParametrTokens.ch, ch + "")
	                .build();
	        placeManager.revealPlace(placeRequest);
		} else {
			//TODO обрываем все связи
			dispatcher.execute(new AddUserAtRoomAction(UserAccount.getUser(), ch), new AsyncCallback<AddUserAtRoomResult>(){

				@Override
				public void onFailure(Throwable arg0) {
					Window.alert(arg0 + "");
				}

				@Override
				public void onSuccess(AddUserAtRoomResult result) {
					logger.log(Level.INFO, "added user " + UserAccount.getUser() + " " + result.getFlag());
					Cookies.setCookie("channel", ch + "");
					
					PlaceRequest placeRequest = new PlaceRequest.Builder()
			                .nameToken(NameTokens.CHAT)
			                .with(ParametrTokens.ch, ch + "")
			                .build();
			        placeManager.revealPlace(placeRequest);
				}
				
			});
		}
	}
}