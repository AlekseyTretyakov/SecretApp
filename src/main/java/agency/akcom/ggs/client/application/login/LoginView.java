package agency.akcom.ggs.client.application.login;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ValidationState;
import org.gwtbootstrap3.client.ui.html.Strong;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormGroup;
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
	Strong err;
	@UiField
	FormGroup formGroupName;
	@UiField
	FormGroup formGroupPass;
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

	@Override
	public void setWarInputs(boolean auth) {
		
		if (auth == false){
			formGroupName.setValidationState(ValidationState.ERROR);
			formGroupPass.setValidationState(ValidationState.ERROR);
			err.setText("Wrong name or password!");
		} else {
			userName.setText("");
			userPass.setText("");
			//formGroupName.setValidationState(ValidationState.SUCCESS);
			//formGroupPass.setValidationState(ValidationState.SUCCESS);
			err.setText("");
		}
	}
}
