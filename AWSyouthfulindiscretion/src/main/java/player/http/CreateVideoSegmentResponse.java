package player.http;

import player.model.VideoSegment;

public class CreateVideoSegmentResponse {
	public final VideoSegment vs;
	public final int statusCode;
	public final String error;
	
	public CreateVideoSegmentResponse(VideoSegment vs, int statusCode){
		this.vs = vs;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public CreateVideoSegmentResponse(int statusCode, String error) {
		this.vs = null;
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "CreateVideoSegmentResponse(" + vs + ")";
	}
}
