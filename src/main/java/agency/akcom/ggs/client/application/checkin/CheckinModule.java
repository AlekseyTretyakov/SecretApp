package agency.akcom.ggs.client.application.checkin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CheckinModule extends AbstractPresenterModule{

	@Override
	protected void configure() {
		bindPresenter(CheckinPresenter.class, CheckinPresenter.MyView.class, CheckinView.class,
				CheckinPresenter.MyProxy.class);
	}

}
