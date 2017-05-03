package agency.akcom.ggs.client.application.some;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import agency.akcom.ggs.client.NameTokens;
import agency.akcom.ggs.client.application.ApplicationPresenter;

public class SomePresenter extends Presenter<SomePresenter.MyView, SomePresenter.MyProxy>{
	interface MyView extends View{
		
	}
	@ProxyStandard
	@NameToken(NameTokens.SOME)
	interface MyProxy extends ProxyPlace<SomePresenter>{
		
	}
	@Inject
	SomePresenter(EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }
}
