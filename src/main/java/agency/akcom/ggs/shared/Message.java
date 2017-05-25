package agency.akcom.ggs.shared;

public class Message {
	private String text;
	private int id;
	private String time;
	private String userName;
	
	public Message(String text, int id, String time, String userName){
		this.id = id;
		this.text = text;
		this.time = time;
		this.userName = userName;
	}
	
	public String getText() {
		return this.text;
	}
	public int getId() {
		return this.id;
	}
	public String getTime() {
		return this.time;
	}
	public String userName() {
		return this.userName;
	}
}
