package agency.akcom.ggs.client.application.some;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class SomeModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(SomePresenter.class, SomePresenter.MyView.class, SomeView.class, SomePresenter.MyProxy.class);
	}
}
