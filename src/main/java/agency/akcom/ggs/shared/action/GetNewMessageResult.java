package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;


@SuppressWarnings("serial")
public class GetNewMessageResult implements Result{
	
	private String[] message;
	private int[] index;
	private String[] user;
	private String[] time;
	private boolean flag = false;
	
	public GetNewMessageResult() { }
	
	public GetNewMessageResult(String[] message, int[] lastIndex, String[] time, String[] user) { 
		this.message = message;
        this.index = lastIndex;
        this.time = time;
        this.user = user;
        
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
}
