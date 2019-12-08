package player.http;

import java.util.ArrayList;
import java.util.List;

import player.model.VideoSegment;

public class AllUnmarkedVideoSegmentsResponse {
	public final List<VideoSegment> segments;
	public final int statusCode;
	public final String error;
	
	public AllUnmarkedVideoSegmentsResponse (List<VideoSegment> list, int code) {
		this.segments = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllUnmarkedVideoSegmentsResponse (int code, String errorMessage) {
		this.segments = new ArrayList<VideoSegment>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (segments == null) { return "EmptyListOfVideoSegments"; }
		return "AllUnmarkedVideoSegments(" + segments.size() + ")";
	}
}
