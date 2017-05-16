package agency.akcom.ggs.client.application.chat;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.PanelBody;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ChatView extends ViewWithUiHandlers<ChatUiHandlers> implements ChatPresenter.MyView {

	interface Binder extends UiBinder<Widget, ChatView> {
	}

	@Inject
	ChatView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField 
	Button sendBtn;
	@UiField
	Button getMsg;
	@UiField
	TextBox textBox;
	@UiField PanelBody msgBlock;
	
	@UiHandler("sendBtn")
	void onClickSend(ClickEvent event){
		getUiHandlers().onSendMessage(textBox.getText());
		textBox.setText("");
	}
	@UiHandler("getMsg")
	void onClickGet(ClickEvent event) {
		//getUiHandlers().onGetMessages();
		getUiHandlers().onTest();
	}
	
	@Override
	public void showMessages(String[] msgs) {
		for (int i = 0; i < msgs.length; i++){
			Label label = new Label(msgs[i]);
			msgBlock.add(label);
		}
	}

	@Override
	public void showAlert(String alert) {
		Window.alert(alert);
	}
	@Override
	public void setEnabledButton() {
		// TODO Auto-generated method stub
		
	}
}
