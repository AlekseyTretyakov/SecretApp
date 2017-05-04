package agency.akcom.ggs.client.application.some;

import org.gwtbootstrap3.client.ui.Button;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class SomeView extends ViewWithUiHandlers<SomeUiHandlers> implements SomePresenter.MyView {

	interface Binder extends UiBinder<Widget, SomeView> {
		
	}
	
	@Inject
	SomeView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
