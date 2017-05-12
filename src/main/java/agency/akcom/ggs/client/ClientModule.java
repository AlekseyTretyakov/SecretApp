package agency.akcom.ggs.client;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

import agency.akcom.ggs.client.application.ApplicationModule;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule.Builder()
                .tokenFormatter(RouteTokenFormatter.class)
                .defaultPlace(NameTokens.LOGIN)
                .errorPlace(NameTokens.LOGIN)
                .unauthorizedPlace(NameTokens.LOGIN)
                .build());

        install(new ApplicationModule());
    }
}
