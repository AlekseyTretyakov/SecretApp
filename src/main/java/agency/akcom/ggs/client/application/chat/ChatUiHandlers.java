package agency.akcom.ggs.client.application.chat;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ChatUiHandlers extends UiHandlers{
	void onSendMessage(String msg);
	void onGetMessages();
	void onTest();
}
