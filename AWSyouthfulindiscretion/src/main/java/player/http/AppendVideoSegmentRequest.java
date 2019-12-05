package player.http;

public class AppendVideoSegmentRequest {
	public final String vsid;
	public final String plid;

	public AppendVideoSegmentRequest(String vsid, String plid) {
		this.plid = plid;
		this.vsid = vsid;
	}
	
	public String toString() {
		return "AppendVideoSegment(" + plid + ", " + vsid + ")";
	}
}

