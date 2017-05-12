package agency.akcom.ggs.client.application.login;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> 
			implements LoginPresenter.MyView {

	interface Binder extends UiBinder<Widget, LoginView> {
	}
	
	@UiField
	TextBox userName;
	@UiField
	Input userPass;
	@UiField
	Button btnConfirm;
	
	@Inject
	LoginView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("btnConfirm")
	void onConfirm(ClickEvent event){
		getUiHandlers().onConfirm(userName.getText(), userPass.getText());
	}
}
