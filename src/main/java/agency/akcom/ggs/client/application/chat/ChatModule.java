package agency.akcom.ggs.client.application.chat;

import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ChatModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new RpcDispatchAsyncModule());
		bindPresenter(ChatPresenter.class, ChatPresenter.MyView.class, ChatView.class, ChatPresenter.MyProxy.class);
	}

}
