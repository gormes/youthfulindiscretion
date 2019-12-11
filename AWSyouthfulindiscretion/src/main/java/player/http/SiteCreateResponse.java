package player.http;

public class SiteCreateResponse {
	public final String url;
	public final int statusCode;
	public final String error;
	
	public SiteCreateResponse(String url, int statusCode) {
		this.url = url;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public SiteCreateResponse(int statusCode, String error) {
		this.url = "";
		this.statusCode = statusCode;
		this.error = error;
	}	
}
