package player.http;

public class RemoveSiteResponse {
	public final String response;
	public final int statusCode;
	public final String error;

	public RemoveSiteResponse(String s, int code) {
		this.response = s;
		this.statusCode = code;
		this.error = "";
	}

	// 200 means success
	public RemoveSiteResponse (String name, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.response = name;
	}
	
}
