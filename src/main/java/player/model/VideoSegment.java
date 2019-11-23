package player.model;

import java.util.UUID;


public class VideoSegment {
	public final String actor;
	public final String phrase;
	public final String url;
	public final UUID id;
	public boolean marked;

	public VideoSegment (String actor, String phrase, String url) {
		this.actor = actor;
		this.phrase = phrase;
		this.url = url;
		this.id = UUID.randomUUID();
		this.marked = false;
	}
	
	public VideoSegment (String actor, String phrase, String url, UUID id) {
		this.actor = actor;
		this.phrase = phrase;
		this.url = url;
		this.id = id;
		this.marked = false;
	}

	public VideoSegment (String actor, String phrase, String url, UUID id, boolean marked) {
		this.actor = actor;
		this.phrase = phrase;
		this.url = url;
		this.id = id;
		this.marked = marked;
	}
	
	public boolean getMarked() {return marked;}
	public void setMarked( boolean m) {marked = m;}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (o instanceof VideoSegment) {
			VideoSegment other = (VideoSegment) o;
			return id.equals(other.id);
		}
		
		return false;  // not a Video Segment
	}
}
