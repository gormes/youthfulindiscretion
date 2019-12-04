package player.http;

import java.util.UUID;

public class PlaylistRequest {
	public final String playlistName;

	public PlaylistRequest() {
		playlistName = UUID.randomUUID().toString();
	}
	
	public PlaylistRequest(String name) {
		playlistName = name;
	}
	
	public String toString() {
		return "CreatePlaylist(" + playlistName + ")";
	}
}
