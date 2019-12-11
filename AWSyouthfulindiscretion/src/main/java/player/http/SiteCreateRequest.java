package player.http;

public class SiteCreateRequest {
	public String url;
	
	public String getURL() {
		return url;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public SiteCreateRequest() {}
	
	public SiteCreateRequest(String url) {
		this.url = url;
	}

}
