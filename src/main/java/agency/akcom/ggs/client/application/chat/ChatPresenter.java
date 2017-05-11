package agency.akcom.ggs.client.application.chat;

import javax.inject.Inject;

import com.google.gwt.user.client.Timer;
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
import agency.akcom.ggs.shared.action.GetNewMessageAction;
import agency.akcom.ggs.shared.action.GetNewMessageResult;

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
	private int lastIndMsg;
	@Inject
	ChatPresenter(
			EventBus eventBus,
			MyView view,
			MyProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
		this.dispatcher = dispatcher;
		lastIndMsg = 0;
		getView().setUiHandlers(this);
		setRequestTimer();
	}
	@Override
	public void onSendMessage(String msg) {
		final String[] msgs = {"User"};
		msgs[0] += " " + msg;
		FetchAdminTaskCountAction action = new FetchAdminTaskCountAction(msg, "user");
		
		
	    //action.setTaskId(1l);
		dispatcher.execute(action, new AsyncCallback<FetchAdminTaskCountResult>(){

			@Override
			public void onFailure(Throwable arg0) {
				getView().showAlert("fail");
				
			}

			@Override
			public void onSuccess(FetchAdminTaskCountResult result) {
				//getView().showAlert("" + result.getMessage());
				msgs[0] += " " + result.getTime();
				getView().showMessages(msgs);
			}
		});
		this.lastIndMsg++;
	}
	@Override
	public void onGetMessages() {
		//Window.alert("" + this.lastIndMsg);
		dispatcher.execute(new GetNewMessageAction(this.lastIndMsg), new AsyncCallback<GetNewMessageResult>(){
			final String[] msgs = {};
			@Override
			public void onFailure(Throwable arg0) {
				getView().showAlert("fail");
				
			}

			@Override
			public void onSuccess(GetNewMessageResult result) {
				String[] text = {};
				String[] user = {};
				String[] time = {};
				int[] index = {};
				text = result.getMessage();
				user = result.getUser();
				time = result.getTime();
				index = result.getIndex();
				if (result.isNotEmpty()) {
					for (int i = 0; i < text.length; i++){
						msgs[i] = user[i] + " " + text[i] + " " + time[i]; 
						lastIndMsg++;
					}
					getView().showMessages(msgs);
				}
			}
		});
	}
	public void setRequestTimer() {
		final int mycount = 0;
		Timer t = new Timer() {
		      @Override
		      public void run() { 
		        onGetMessages();
		      }
		    };
	    t.scheduleRepeating(3000);
	}
}
