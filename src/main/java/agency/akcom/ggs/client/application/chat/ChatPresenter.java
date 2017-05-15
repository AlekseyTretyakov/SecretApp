package agency.akcom.ggs.client.application.chat;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import agency.akcom.ggs.client.NameTokens;
import agency.akcom.ggs.client.application.ApplicationPresenter;
import agency.akcom.ggs.client.security.UserAccount;
import agency.akcom.ggs.shared.action.FetchAdminTaskCountAction;
import agency.akcom.ggs.shared.action.FetchAdminTaskCountResult;
import agency.akcom.ggs.shared.action.GetAliasKeyAction;
import agency.akcom.ggs.shared.action.GetAliasKeyResult;
import agency.akcom.ggs.shared.action.GetNewMessageAction;
import agency.akcom.ggs.shared.action.GetNewMessageResult;
import agency.akcom.ggs.shared.action.GetOpenValuesAction;
import agency.akcom.ggs.shared.action.GetOpenValuesResult;
import agency.akcom.ggs.shared.action.SendOpenKeyToServerAction;
import agency.akcom.ggs.shared.action.SendOpenKeyToServerResult;
import agency.akcom.ggs.shared.action.SukaAction;
import agency.akcom.ggs.shared.action.SukaResult;
import agency.akcom.ggs.shared.crypt.Crypto;

public class ChatPresenter extends Presenter<ChatPresenter.MyView, ChatPresenter.MyProxy>  
		implements ChatUiHandlers {
	
	interface MyView extends View, HasUiHandlers<ChatUiHandlers> {
		void showMessages(String[] msgs);
		void showAlert(String alert);
	}
	@ProxyStandard
	@NameToken(NameTokens.CHAT)
	@NoGatekeeper
	interface MyProxy extends ProxyPlace<ChatPresenter> {
		
	}
	private final DispatchAsync dispatcher;	
	private int lastIndMsg;
	private double openKey;
	private double alienOpenKey;
	private int secretValue;
	private int cryptValP;
	private int cryptValG;
	private double secretKey;
	Logger logger = Logger.getLogger("TestLogger2");
	
	@Inject
	ChatPresenter(
			EventBus eventBus,
			MyView view,
			MyProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
		this.dispatcher = dispatcher;
		lastIndMsg = 0;
		getView().setUiHandlers(this);

		createOpenKey();
		/*
		 * Search public key at localStorage or create new
		 */
		/*
		 * Request messages from the ChatServer
		 */
		//setRequestTimer();
	}
	@Override
	public void onSendMessage(String msg) {
		final String[] msgs = {"User"};
		msgs[0] += " " + msg;
		FetchAdminTaskCountAction action = new FetchAdminTaskCountAction(msg, "user");
		
		//Do ecnrypt message
		dispatcher.execute(action, new AsyncCallback<FetchAdminTaskCountResult>(){

			@Override
			public void onFailure(Throwable arg0) {
				getView().showAlert("fail");
				
			}

			@Override
			public void onSuccess(FetchAdminTaskCountResult result) {
				//getView().showAlert("" + result.getMessage());
				msgs[0] += " " + result.getTime();
				getView().showMessages(msgs);
			}
		});
		this.lastIndMsg++;
	}
	@Override
	public void onGetMessages() {
		//Window.alert("" + this.lastIndMsg);
		dispatcher.execute(new GetNewMessageAction(this.lastIndMsg), new AsyncCallback<GetNewMessageResult>(){
			final String[] msgs = {};
			@Override
			public void onFailure(Throwable arg0) {
				getView().showAlert("fail");
				
			}

			@Override
			public void onSuccess(GetNewMessageResult result) {
				String[] text = {};
				String[] user = {};
				String[] time = {};
				int[] index = {};
				text = result.getMessage();
				user = result.getUser();
				time = result.getTime();
				index = result.getIndex();
				if (result.isNotEmpty()) {
					for (int i = 0; i < text.length; i++){
						msgs[i] = user[i] + " " + text[i] + " " + time[i]; 
						lastIndMsg++;
					}
					getView().showMessages(msgs);
				}
			}
		});
	}
	public void setRequestTimer() {
		final int mycount = 0;
		Timer t = new Timer() {
		      @Override
		      public void run() { 
		        onGetMessages();
		      }
		    };
	    t.scheduleRepeating(3000);
	}
	public void createOpenKey() {
		
		GetOpenValuesAction action = new GetOpenValuesAction();
		//Window.alert(action.getServiceName() + " " + action.isSecured());
		dispatcher.execute(action, new AsyncCallback<GetOpenValuesResult>(){

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("fail");
			}
			/*
			 * Save keys at localStorage
			 */
			@Override
			public void onSuccess(GetOpenValuesResult result) {
				secretValue = Crypto.randomSecretKey();
				cryptValP = result.getValueP();
				cryptValG = result.getValueG();
				openKey = Crypto.getOpenKey(secretValue, cryptValP, cryptValG);
				logger.log(Level.INFO, "open key: " + openKey);
				logger.log(Level.INFO, "user: " + UserAccount.getUser());
				//getView().showAlert("open key: " + openKey);
				sendPublicKeyToServer(UserAccount.getUser(), openKey);
			}
			
		});
		
	}
	public void sendPublicKeyToServer(String user, double openKey) {
		dispatcher.execute(new SendOpenKeyToServerAction(user, openKey), new AsyncCallback<SendOpenKeyToServerResult>(){

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(SendOpenKeyToServerResult arg0) {
				//Window.alert("Key saved successfully");
				
			}});
	}
	public void createSharedSecretKey() {
		GetAliasKeyAction action = new GetAliasKeyAction(UserAccount.getUser());
		
		dispatcher.execute(action, new AsyncCallback<GetAliasKeyResult>(){

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("error");
			}

			@Override
			public void onSuccess(GetAliasKeyResult result) {
				// TODO Auto-generated method stub
				alienOpenKey = result.getKey();
				secretKey = Crypto.getSahredSecretKey(alienOpenKey, secretValue, cryptValP);
				Window.alert(secretKey + "");
			}});
	}
	@Override
	public void onTest() {
		Window.alert(UserAccount.getUser() + "");
		createSharedSecretKey();
	}
	public void callBack(){
		getView().showAlert("test");
	}
}
