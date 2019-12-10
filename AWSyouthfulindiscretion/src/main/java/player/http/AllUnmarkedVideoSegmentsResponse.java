package player.http;

import java.util.ArrayList;
import java.util.List;

public class AllUnmarkedVideoSegmentsResponse {
	public final List<RemoteSegmentResponseObject> segments;
	public final int statusCode;
	public final String error;
	
	public AllUnmarkedVideoSegmentsResponse (List<RemoteSegmentResponseObject> list, int code) {
		this.segments = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllUnmarkedVideoSegmentsResponse (int code, String errorMessage) {
		this.segments = new ArrayList<RemoteSegmentResponseObject>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (segments == null) { return "EmptyListOfVideoSegments"; }
		return "AllUnmarkedVideoSegments(" + segments.size() + ")";
	}
}
