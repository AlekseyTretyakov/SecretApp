package agency.akcom.ggs.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import agency.akcom.ggs.client.application.chat.ChatModule;
import agency.akcom.ggs.client.application.checkin.CheckinModule;
import agency.akcom.ggs.client.application.home.HomeModule;
import agency.akcom.ggs.client.application.login.LoginModule;
import agency.akcom.ggs.client.application.some.SomeModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());
        install(new SomeModule());
        install(new ChatModule());
        install(new LoginModule());
        install(new CheckinModule());
        
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}