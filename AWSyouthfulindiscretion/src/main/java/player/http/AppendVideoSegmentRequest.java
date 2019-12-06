package player.http;

public class AppendVideoSegmentRequest {
	public String vsid;
	public String plid;

	public AppendVideoSegmentRequest(String vsid, String plid) {
		this.plid = plid;
		this.vsid = vsid;
	}
	
	public AppendVideoSegmentRequest() {
		
	}
	
	public String getVideoSegmentID() {
		return vsid;
	}
	public void setVideoSegmentID(String vsid) {
		this.vsid = vsid;
	}
	
	public String getPlaylistID() {
		return plid;
	}
	public void setPlaylistID(String plid) {
		this.plid = plid;
	}	
	
	public String toString() {
		return "AppendVideoSegment(" + vsid + " , " + vsid + ")";
	}
}

