package player.http;

import player.model.VideoSegment;

public class VideoSegmentMarkRequest {
	private String url;
	private boolean marked;
	
	public VideoSegmentMarkRequest() {}
	
	public VideoSegmentMarkRequest(String url, boolean marked) {
		this.url = url;
		this.marked = marked;
	}
	
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean getMarked() {
		return marked;
	}
	
	public void setMarked(boolean marked) {
		this.marked = marked;
	}

}
