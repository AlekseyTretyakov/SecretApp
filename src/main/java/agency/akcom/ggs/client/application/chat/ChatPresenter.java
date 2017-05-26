package agency.akcom.ggs.client.application.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.Cookies;
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
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import agency.akcom.ggs.client.NameTokens;
import agency.akcom.ggs.client.application.ApplicationPresenter;
import agency.akcom.ggs.client.security.UserAccount;
import agency.akcom.ggs.shared.Message;
import agency.akcom.ggs.shared.action.FetchAdminTaskCountAction;
import agency.akcom.ggs.shared.action.FetchAdminTaskCountResult;
import agency.akcom.ggs.shared.action.GetAliasKeyAction;
import agency.akcom.ggs.shared.action.GetAliasKeyResult;
import agency.akcom.ggs.shared.action.GetNewMessageAction;
import agency.akcom.ggs.shared.action.GetNewMessageResult;
import agency.akcom.ggs.shared.action.GetOpenValuesAction;
import agency.akcom.ggs.shared.action.GetOpenValuesResult;
import agency.akcom.ggs.shared.action.GetUserListAtRoomAction;
import agency.akcom.ggs.shared.action.GetUserListAtRoomResult;
import agency.akcom.ggs.shared.action.SendMessageAction;
import agency.akcom.ggs.shared.action.SendMessageResult;
import agency.akcom.ggs.shared.action.SendOpenKeyToServerAction;
import agency.akcom.ggs.shared.action.SendOpenKeyToServerResult;
import agency.akcom.ggs.shared.crypt.Crypto;

public class ChatPresenter extends Presenter<ChatPresenter.MyView, ChatPresenter.MyProxy>  
		implements ChatUiHandlers {
	
	interface MyView extends View, HasUiHandlers<ChatUiHandlers> {
		void showMessages(String[] msgs);
		void showAlert(String alert);
		void setEnabledButton();
		void showMsgs(List<Message> msg);
	}
	@ProxyStandard
	@NameToken(NameTokens.CHAT)
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
	private Timer timer;
	private int room;
	private int counter = 0;
	private PlaceManager placeManager;
	Logger logger = Logger.getLogger("TestLogger2");
	
	@Inject
	ChatPresenter(
			EventBus eventBus,
			MyView view,
			MyProxy proxy, final DispatchAsync dispatcher,
			PlaceManager placeManager) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
		this.dispatcher = dispatcher;
		lastIndMsg = 0;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);

		/*if (UserAccount.getAccess() == true){
			room = Integer.parseInt(placeManager.getCurrentPlaceRequest().getParameter("ch", ""));
			logger.log(Level.INFO, "channel " +  room + "; user " + UserAccount.getUser());
			createOpenKey();
		} else {
			gotoLogin();
		}*/
		room = Integer.parseInt(placeManager.getCurrentPlaceRequest().getParameter("ch", ""));
		logger.log(Level.INFO, "channel " +  room + "; user " + UserAccount.getUser());
		createOpenKey();
	}
	public void onSendMessage2(String msg) {
		final String[] msgs = {UserAccount.getUser()};
		msgs[0] += " " + msg;
		
		String cmsg = Crypto.crypt(msg, secretKey);
		logger.log(Level.INFO, cmsg);
		
		FetchAdminTaskCountAction action = new FetchAdminTaskCountAction(cmsg, UserAccount.getUser());
		
		dispatcher.execute(action, new AsyncCallback<FetchAdminTaskCountResult>(){

			@Override
			public void onFailure(Throwable arg0) {
				getView().showAlert(arg0 + "");
				
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
	public void onSendMessage(final String msg) {
		dispatcher.execute(new SendMessageAction(Crypto.crypt(msg, secretKey), UserAccount.getUser()), new AsyncCallback<SendMessageResult>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught + "");
			}

			@Override
			public void onSuccess(SendMessageResult result) {
				List<Message> ms = new ArrayList<>();
				ms.add(new Message(msg, result.getIndex(), result.getDate(), UserAccount.getUser()));
				getView().showMsgs(ms);
			}});
		this.lastIndMsg++;
	}
	@Override
	public void onGetMessages() {
		//Window.alert("" + this.lastIndMsg);
		dispatcher.execute(new GetNewMessageAction(this.lastIndMsg), new AsyncCallback<GetNewMessageResult>(){
			final String[] msgs = {};
			@Override
			public void onFailure(Throwable arg0) {
				getView().showAlert(arg0 + "");
				
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
				List<Message> ms = new ArrayList<>();
				if (result.isNotEmpty()) {
					for (int i = 0; i < text.length; i++){
						logger.log(Level.INFO, text[i]);
						String txt = Crypto.decrypt(text[i], secretKey);
						ms.add(new Message(txt, index[i], time[i], user[i]));
						msgs[i] = user[i] + " " + txt + " " + time[i]; 
						lastIndMsg++;
					}
					getView().showMsgs(ms);
				}
			}
		});
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
				logger.log(Level.INFO, "secret value: " + secretValue + " cryptValP: " 
						+ cryptValP + " cryptValG: " + cryptValG);
				openKey = Crypto.getOpenKey(secretValue, cryptValP, cryptValG);
				//Window.alert(openKey + "");
				//Window.alert(UserAccount.getUser());
				logger.log(Level.INFO, "open key: " + openKey);
				logger.log(Level.INFO, "user: " + UserAccount.getUser());
				//getView().showAlert("open key: " + openKey);
				UserAccount.setKey(openKey);
				Cookies.setCookie("key", openKey + "");
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
				
				getCountUserInRoom();
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
				//logger.log(Level.INFO, "okey " + openKey + "; sval " + secretValue + "; alienOpenKey " + alienOpenKey);
				//logger.log(Level.INFO, "secret key: " + secretKey);
				getView().setEnabledButton();
				
				Timer timer = new Timer() {

					@Override
					public void run() {
						onGetMessages();
					}
					
				};
				timer.scheduleRepeating(2000);
				
				//Window.alert(secretKey + "");
			}});
	}
	public void getCountUserInRoom() {
		//final int room = 0;
		dispatcher.execute(new GetUserListAtRoomAction(room), new AsyncCallback<GetUserListAtRoomResult>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert(caught + "");
			}

			@Override
			public void onSuccess(GetUserListAtRoomResult result) {
				/*logger.log(Level.INFO, "users in room " + room + " " + result.getUsers().size());
				for (String s : result.getUsers()) {
					logger.log(Level.INFO, "name " + s);
				}*/
				if (result.getUsers().size() == 2){
					logger.log(Level.INFO, "users in room " + room + " " + result.getUsers().size());
					createSharedSecretKey();
				} else {
					Timer timer = new Timer() {

						@Override
						public void run() {
							getCountUserInRoom();
						}
						
					};
					timer.schedule(2000);
					//getCountUserInRoom();
				}
				 
			}});
	}
	@Override
	public void onTest() {
		//Window.alert(UserAccount.getUser() + "");
		//getCountUserInRoom();
		//onGetMessages();
		onSendMessage("Hi boys");
		//test();
	}
	public void callBack(){
		getView().showAlert("test");
	}
	public void test() {
		final String mess = "hello";
		Window.alert(mess);
		FetchAdminTaskCountAction action = new FetchAdminTaskCountAction(mess, UserAccount.getUser());
		
		dispatcher.execute(action, new AsyncCallback<FetchAdminTaskCountResult>(){

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert(arg0 + "");
				
			}

			@Override
			public void onSuccess(FetchAdminTaskCountResult result) {
				//getView().showAlert("" + result.getMessage());
				//getView().showMsgs(mess, UserAccount.getUser(), result.getTime());
			}
		});
		this.lastIndMsg++;
	}
	public void setRequestTimer() {
		
		timer = new Timer() {
		      @Override
		      public void run() { 
		    	  getCountUserInRoom();
		      }
		    };
	    timer.scheduleRepeating(3000);
	}
	public void gotoLogin() {
		PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.LOGIN)
                .build();
        placeManager.revealPlace(placeRequest);
	}
}
