package player.model;

import java.util.UUID;


public class VideoSegment {
	public final String fileName;
	public final String actor;
	public final String phrase;
	public final UUID id;
	public boolean marked;

	public VideoSegment (String fileName, String actor, String phrase) {
		this.fileName = fileName;
		this.actor = actor;
		this.phrase = phrase;
		this.id = UUID.randomUUID();
		this.marked = false;
	}
	
	public VideoSegment (String fileName, String actor, String phrase, UUID id, boolean marked) {
		this.fileName = fileName;
		this.actor = actor;
		this.phrase = phrase;
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
