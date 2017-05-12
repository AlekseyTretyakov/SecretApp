package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class GetOpenValuesResult implements Result{
	private int p;
	private int g;
	
	public GetOpenValuesResult() {}
	public GetOpenValuesResult(int p, int g) {
		this.p = p;
		this.g = g;
	}
	public int getValueP() { return this.p; }
	public int getValueG() { return this.g; }
}
