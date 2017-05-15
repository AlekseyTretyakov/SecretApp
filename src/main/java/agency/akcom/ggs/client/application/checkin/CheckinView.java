package agency.akcom.ggs.client.application.checkin;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormGroup;
import org.gwtbootstrap3.client.ui.FormLabel;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ValidationState;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class CheckinView extends ViewWithUiHandlers<CheckinUiHandlers> 
	implements CheckinPresenter.MyView {

	interface Binder extends UiBinder<Widget, CheckinView> {
		
	}
	@UiField
	FormGroup formGroupName;
	@UiField
	FormGroup formGroupPass1;
	@UiField
	FormGroup formGroupPass2;
	@UiField
	TextBox userName;
	@UiField
	Label stateMsg;
	@UiField 
	Input userPass1;
	@UiField 
	Input userPass2;
	@UiField
	FormLabel labelName;
	@UiField
	FormLabel labelPass1;
	@UiField
	FormLabel labelPass2;
	@UiField
	Button btnConfirm;
	
	@Inject
	CheckinView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	@UiHandler("btnConfirm")
	void onConfirm(ClickEvent ecvent){
		if (userName.getText() == "" || userPass1.getText() == "" || 
				userPass2.getText() == ""){
			formGroupName.setValidationState(ValidationState.ERROR);
			formGroupPass1.setValidationState(ValidationState.ERROR);
			formGroupPass2.setValidationState(ValidationState.ERROR);
			stateMsg.setText("Wrong");
		} else {
			formGroupName.setValidationState(ValidationState.SUCCESS);
			formGroupPass1.setValidationState(ValidationState.SUCCESS);
			formGroupPass2.setValidationState(ValidationState.SUCCESS);
			
			getUiHandlers().onRegUser(userName.getText(), userPass1.getText());
		}
	}
	/*@UiHandler("userName")
	void onEnterName(ChangeEvent event){
		getUiHandlers().onCheckUserName(userName.getText());
	}*/
	@UiHandler("userName")
	void onEnterName(KeyUpEvent event){
		if (userName.getText().length() < 4){
			labelName.setText("The name must be at least 4 characters");
			formGroupName.setValidationState(ValidationState.ERROR);
		} else {
			getUiHandlers().onCheckUserName(userName.getText());
		}
			
	}
	@UiHandler("userPass1")
	void onEnterPass1(KeyUpEvent event) {
		if (userPass1.getText().length() < 4) {
			formGroupPass1.setValidationState(ValidationState.ERROR);
			labelPass1.setText("Password is too short!!");
		} else {
			formGroupPass1.setValidationState(ValidationState.SUCCESS);
			labelPass1.setText("Password");
		}
	}
	@UiHandler("userPass2")
	void onChangePass(KeyUpEvent event){
		if(userPass1.getText() != userPass2.getText()){
			formGroupPass1.setValidationState(ValidationState.ERROR);
			formGroupPass2.setValidationState(ValidationState.ERROR);
			labelPass2.setText("Passwords are different!");
		} else {
			formGroupPass1.setValidationState(ValidationState.SUCCESS);
			formGroupPass2.setValidationState(ValidationState.SUCCESS);
			labelPass2.setText("Password");
		}
	}

	@Override
	public void checkUserName(boolean check) {
		if (check){
			formGroupName.setValidationState(ValidationState.SUCCESS);
			labelName.setText("Name");
		} else {
			formGroupName.setValidationState(ValidationState.ERROR);
			labelName.setText("This name is already use");
		}
	}
}
