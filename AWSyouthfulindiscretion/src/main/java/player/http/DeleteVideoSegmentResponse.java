package player.http;

public class DeleteVideoSegmentResponse {
	public final String response;
	public final int statusCode;
	public final String error;

	public DeleteVideoSegmentResponse(String s, int code) {
		this.response = s;
		this.statusCode = code;
		this.error = ""; 
	}

	// 200 means success
	public DeleteVideoSegmentResponse (String response, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.response = response;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "DeleteResponse(" + response + ")";
		} else {
			return "ErrorResult(" + response + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
