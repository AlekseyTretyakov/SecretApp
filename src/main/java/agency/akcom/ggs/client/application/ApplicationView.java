package agency.akcom.ggs.client.application;

import org.gwtbootstrap3.client.ui.NavbarLink;
import org.gwtbootstrap3.client.ui.html.Strong;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationPresenter.MyView{

	interface Binder extends UiBinder<Widget, ApplicationView> {
	}
	
	@UiField 
	SimplePanel contentContainer;
	@UiField
	Strong userNameField;
	@UiField
	NavbarLink logIn;
	@UiField
	NavbarLink checkIn;
	@UiField
	NavbarLink logOut;
	
	@Inject
	ApplicationView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	/*@UiHandler("home")
	void onClickHome(ClickEvent e){
		getUiHandlers().onClickHome();
	}
	@UiHandler("some")
	void onClickSome(ClickEvent e){
		getUiHandlers().onClickSome();
	}*/
	 @Override
	 public void setInSlot(Object slot, IsWidget content) {
		 if (slot == ApplicationPresenter.SLOT_MAIN) {
			 contentContainer.setWidget(content);
		 } else {
			 super.setInSlot(slot, content);
		 }
	 }
	@Override
	public void setUserName(String name) {
		userNameField.setText("Hello, " +  name + "!");
		logIn.setVisible(false);
		checkIn.setVisible(false);
		logOut.setVisible(true);
	}
	
	@UiHandler("logIn")
	void goToLogin(ClickEvent event){
		getUiHandlers().onLogin();
	}
	@UiHandler("checkIn")
	void goToCheckin(ClickEvent event){
		getUiHandlers().onCheckin();
	}
	@UiHandler("logOut")
	void goToLogout(ClickEvent event){
		userNameField.setText("Hello, Guest!");
		logIn.setVisible(true);
		checkIn.setVisible(true);
		logOut.setVisible(false);
		getUiHandlers().onLogout();
	}
}
