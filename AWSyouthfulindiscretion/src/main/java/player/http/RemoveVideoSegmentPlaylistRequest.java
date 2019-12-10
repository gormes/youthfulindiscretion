package player.http;

import java.util.UUID;

public class RemoveVideoSegmentPlaylistRequest {
	private String playlistID;
	private String videoSegmentURL;
	
	public RemoveVideoSegmentPlaylistRequest(String playlistID, String videoSegmentURL) {
		  this.playlistID = playlistID;
		  this.videoSegmentURL = videoSegmentURL;
	}
	
	public String getPlaylistID() {
		return playlistID;
	}
	
	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}
	
	public String getVideoSegmentURL() {
		return videoSegmentURL;
	}
	
	public void setVideoSegmetnURL(String videoSegmentURL) {
		this.videoSegmentURL = videoSegmentURL;
	}
}
