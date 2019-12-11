package player.http;

public class RemoveSiteRequest {
	public String url;

	public RemoveSiteRequest() {
	}
	
	public String getID() {return url;}
	public void setID(String url) {this.url = url;}
	
	public RemoveSiteRequest(String url) {
		this.url = url;
	}


}
