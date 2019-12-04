package player.http;

public class DeletePlaylistResponse {
	public final String response;
	public final int statusCode;
	public final String error;

	public DeletePlaylistResponse(String s, int code) {
		this.response = s;
		this.statusCode = code;
		this.error = "";
	}

	// 200 means success
	public DeletePlaylistResponse (String name, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.response = name;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "DeleteResponse(" + response + ")";
		} else {
			return "ErrorResult(" + response + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
