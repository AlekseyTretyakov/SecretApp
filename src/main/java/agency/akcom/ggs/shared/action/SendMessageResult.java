package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class SendMessageResult implements Result{
	
	private String date;
	private int index;
	
	public SendMessageResult() {}
	
	public SendMessageResult(String date, int index) {
		this.date = date;
		this.index = index;
	}
	public String getDate() { return date; }
	public int getIndex() { return index; }
}
