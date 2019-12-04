package player.http;

public class CreatePlaylistResponse {

	public final String response;
	public final int httpCode;
	
	public CreatePlaylistResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public CreatePlaylistResponse (String s) {
		this.response = s;
		this.httpCode = 201;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}

}
