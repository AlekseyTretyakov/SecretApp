package agency.akcom.ggs.client.application.checkin;

import com.gwtplatform.mvp.client.UiHandlers;

public interface CheckinUiHandlers extends UiHandlers{
	void onRegUser(String name, String pass);
	void onCheckUserName(String name);
}
