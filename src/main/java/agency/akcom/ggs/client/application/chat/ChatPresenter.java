package agency.akcom.ggs.client.application.chat;

import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import agency.akcom.ggs.client.NameTokens;
import agency.akcom.ggs.client.application.ApplicationPresenter;
import agency.akcom.ggs.shared.action.FetchAdminTaskCountAction;
import agency.akcom.ggs.shared.action.FetchAdminTaskCountResult;

public class ChatPresenter extends Presenter<ChatPresenter.MyView, ChatPresenter.MyProxy>  
		implements ChatUiHandlers {
	
	interface MyView extends View, HasUiHandlers<ChatUiHandlers> {
		void showMessages(String[] msgs);
		void showAlert(String alert);
	}
	@ProxyStandard
	@NameToken(NameTokens.CHAT)
	interface MyProxy extends ProxyPlace<ChatPresenter> {
		
	}
	private final DispatchAsync dispatcher;	
	@Inject
	ChatPresenter(
			EventBus eventBus,
			MyView view,
			MyProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}
	@Override
	public void onSendMessage(String msg) {
		String[] msgs = {"Olala", "Hi"};
		msgs[1] += msg;
		FetchAdminTaskCountAction action = new FetchAdminTaskCountAction(msg);
		
		
	    //action.setTaskId(1l);
		dispatcher.execute(action, new AsyncCallback<FetchAdminTaskCountResult>(){

			@Override
			public void onFailure(Throwable arg0) {
				getView().showAlert("fail");
				
			}

			@Override
			public void onSuccess(FetchAdminTaskCountResult result) {
				getView().showAlert("" + result.getMessage());
			}
		});
		
		
		getView().showMessages(msgs);
	}
	@Override
	public void onGetMessages(String[] msgs) {
		// TODO Auto-generated method stub
		
	}
}
