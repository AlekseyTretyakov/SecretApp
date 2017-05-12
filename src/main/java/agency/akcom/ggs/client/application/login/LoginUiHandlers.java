package agency.akcom.ggs.client.application.login;

import com.gwtplatform.mvp.client.UiHandlers;

public interface LoginUiHandlers extends UiHandlers{
	void onConfirm(String userName, String userPass);
}
