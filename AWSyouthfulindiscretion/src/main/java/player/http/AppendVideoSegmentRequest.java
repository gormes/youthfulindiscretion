package player.http;

public class AppendVideoSegmentRequest {
	public String vsurl;
	public String plid;

	public AppendVideoSegmentRequest(String vsurl, String plid) {
		this.plid = plid;
		this.vsurl = vsurl;
	}
	
	public AppendVideoSegmentRequest() {
		
	}
	
	public String getVideoSegmentID() {
		return vsurl;
	}
	public void setVideoSegmentID(String vsurl) {
		this.vsurl = vsurl;
	}
	
	public String getPlaylistID() {
		return plid;
	}
	public void setPlaylistID(String plid) {
		this.plid = plid;
	}	
}

