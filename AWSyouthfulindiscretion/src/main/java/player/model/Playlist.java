package player.model;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {
	public final UUID id;
	public ArrayList<VideoSegment> videoSegments;
	
	public Playlist(UUID id) {
		this.id = id;
		videoSegments = new ArrayList<VideoSegment>();
	}
	
	public Playlist() {
		this.id = UUID.randomUUID();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (o instanceof Playlist) {
			Playlist other = (Playlist) o;
			return id.equals(other.id);
		}
		
		return false;  // not a Playlist
	}
}
