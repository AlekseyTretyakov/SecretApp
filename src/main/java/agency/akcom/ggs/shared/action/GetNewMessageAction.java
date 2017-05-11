package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class GetNewMessageAction extends UnsecuredActionImpl<GetNewMessageResult>{
	
	private int lastIndex;
	
	public GetNewMessageAction() {
    }
	public GetNewMessageAction(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getIndex() { return this.lastIndex; }
}
