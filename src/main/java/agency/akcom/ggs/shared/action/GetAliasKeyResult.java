package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class GetAliasKeyResult implements Result{
	
	private double aliasOpenKey;
	
	public GetAliasKeyResult() {}
	
	public GetAliasKeyResult(double key) {
		this.aliasOpenKey = key;
	}
	public double getKey() {
		return aliasOpenKey;
	}
}
