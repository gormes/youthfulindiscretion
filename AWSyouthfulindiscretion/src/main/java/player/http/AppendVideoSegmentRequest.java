package player.http;

public class AppendVideoSegmentRequest {
	public String vsurl;
	public String plid;

	public AppendVideoSegmentRequest(String vsid, String plid) {
		this.plid = plid;
		this.vsurl = vsid;
	}
	
	public AppendVideoSegmentRequest() {
		
	}
	
	public String getVideoSegmentID() {
		return vsurl;
	}
	public void setVideoSegmentID(String vsid) {
		this.vsurl = vsid;
	}
	
	public String getPlaylistID() {
		return plid;
	}
	public void setPlaylistID(String plid) {
		this.plid = plid;
	}	
	
	public String toString() {
		return "AppendVideoSegment(" + vsurl + " , " + plid + ")";
	}
}

