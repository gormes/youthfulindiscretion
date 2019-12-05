package player.http;


public class AppendVideoSegmentResponse {
	public final int code;
	public final String response;
	
	public AppendVideoSegmentResponse(String s, int code) {
		this.code = code;
		this.response = s;
	}
	
	public AppendVideoSegmentResponse(String s) {
		this.code = 200;
		this.response = s;
	}
	
	
	public String toString() {
		if(code==400) { return "Error";}
		else {
		return response;
		}
	}

}
