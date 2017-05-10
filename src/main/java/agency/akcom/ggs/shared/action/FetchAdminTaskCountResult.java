package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class FetchAdminTaskCountResult implements Result{
	private Integer totalTasks;
	private String message;
	
    public FetchAdminTaskCountResult() {
    }

    public FetchAdminTaskCountResult(String message) {
        this.message = message;
    }

    public Integer getTotalTasksCount() {
        return totalTasks;
    }
    public String getMessage() {
    	return this.message;
    }
}
