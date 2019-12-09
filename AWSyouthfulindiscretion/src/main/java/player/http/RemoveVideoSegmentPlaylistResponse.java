package player.http;

public class RemoveVideoSegmentPlaylistResponse {
	public final int statusCode;
	public final String message;
	
	public RemoveVideoSegmentPlaylistResponse(int statusCode, String message) {
		this.statusCode= statusCode;
		this.message = message;
	}
}
