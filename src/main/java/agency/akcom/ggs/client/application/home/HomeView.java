package agency.akcom.ggs.client.application.home;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {

	interface Binder extends UiBinder<Widget, HomeView> {
	}
	
	@UiField
	Button bch1;
	
	@Inject
	HomeView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("bch1")
	void chooseChanel1(ClickEvent event) {
		getUiHandlers().onChooseChanel1(0);
	}

	@Override
	public void setButtonEnabled(int num, boolean flag) {
		//Document.get().getElementById("bch1");
		if (num == 0) {
			if (flag == true)
				bch1.setEnabled(true);
			else { 
				bch1.setEnabled(false);
				//bch1.setType();
			}
		}
	}
}
