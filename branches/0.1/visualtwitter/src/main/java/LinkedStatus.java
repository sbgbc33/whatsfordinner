import twitter4j.Status;


public class LinkedStatus {

	private Status status;
	private LinkedStatus parentStatus;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LinkedStatus getParentStatus() {
		return parentStatus;
	}
	public void setParentStatus(LinkedStatus parentStatus) {
		this.parentStatus = parentStatus;
	}
}
