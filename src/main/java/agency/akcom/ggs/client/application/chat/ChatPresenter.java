package agency.akcom.ggs.client.application.chat;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import agency.akcom.ggs.client.NameTokens;
import agency.akcom.ggs.client.application.ApplicationPresenter;

public class ChatPresenter extends Presenter<ChatPresenter.MyView, ChatPresenter.MyProxy>  
		implements ChatUiHandlers {
	
	interface MyView extends View, HasUiHandlers<ChatUiHandlers> {
		void showMessages(String[] msgs);
	}
	@ProxyStandard
	@NameToken(NameTokens.CHAT)
	interface MyProxy extends ProxyPlace<ChatPresenter> {
		
	}
	@Inject
	ChatPresenter(
			EventBus eventBus,
			MyView view,
			MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
		
		getView().setUiHandlers(this);
	}
	@Override
	public void onSendMessage(String msg) {
		String[] msgs = {"Olala", "Hi"};
		msgs[1] += msg;
		getView().showMessages(msgs);
	}
	@Override
	public void onGetMessages(String[] msgs) {
		// TODO Auto-generated method stub
		
	}
}
