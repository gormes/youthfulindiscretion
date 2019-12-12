package player.http;

public class VideoSegmentMarkResponse {
	public final int statusCode;
	public final String message;
	
	public VideoSegmentMarkResponse(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

}
