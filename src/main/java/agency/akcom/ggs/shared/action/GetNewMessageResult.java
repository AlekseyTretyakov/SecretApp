package agency.akcom.ggs.shared.action;

import java.util.ArrayList;
import java.util.List;

import com.gwtplatform.dispatch.rpc.shared.Result;

import agency.akcom.ggs.shared.Message;

@SuppressWarnings("serial")
public class GetNewMessageResult implements Result{
	
	private String[] message;
	private int[] index;
	private String[] user;
	private String[] time;
	private boolean flag = false;
	private List<Message> msgs = new ArrayList<>();
	
	public GetNewMessageResult() { }
	
	public GetNewMessageResult(String[] message, int[] lastIndex, String[] time, String[] user) { 
		this.message = message;
        this.index = lastIndex;
        this.time = time;
        this.user = user;
        for (int i = 0; i < message.length; i++){
        	msgs.add(new Message(message[i], lastIndex[i], time[i], user[i]));
        }
        
        flag = true;
	}
	public boolean isNotEmpty() {
		return flag;
	}
	public String[] getMessage() {
    	return this.message;
    }
    public int[] getIndex() {
    	return this.index;
    }
    public String[] getTime() {
    	return this.time;
    }
    public String[] getUser() {
    	return this.user;
    }
    public List<Message> getMessages() {
    	return msgs;
    }
}
