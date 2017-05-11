package agency.akcom.ggs.shared.action;

import com.gwtplatform.dispatch.rpc.shared.Result;

@SuppressWarnings("serial")
public class FetchAdminTaskCountResult implements Result{
	private Integer totalTasks;
	private String message;
	private int lastIndex;
	private String time;
	
    public FetchAdminTaskCountResult() {
    }

    public FetchAdminTaskCountResult(String message, int lastIndex, String time) {
        this.message = message;
        this.lastIndex = lastIndex;
        this.time = time;
    }

    public Integer getTotalTasksCount() {
        return totalTasks;
    }
    public String getMessage() {
    	return this.message;
    }
    public int getIndex() {
    	return this.lastIndex;
    }
    public String getTime() {
    	return this.time;
    }
}
