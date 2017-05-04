package agency.akcom.ggs.client.application;

import javax.inject.Inject;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HasHandlers;
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

public class ApplicationPresenter extends Presenter<MyView, MyProxy> implements ApplicationUiHandlers{
    interface MyView extends View, HasUiHandlers<ApplicationUiHandlers>{
    }

    @ProxyStandard
    interface MyProxy extends Proxy<ApplicationPresenter> {
    }
    
    private PlaceManager placeManager;
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<>();
    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    @Inject
    ApplicationPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, RevealType.Root);
        
        this.placeManager = placeManager;
        
    	getView().setUiHandlers(this);
    }

	/*@Override
	public void onClickHome() {
		PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.HOME)
                .build();
        placeManager.revealPlace(request);
	}

	@Override
	public void onClickSome() {
		PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.SOME)
                .build();
        placeManager.revealPlace(request);
	}*/
}
