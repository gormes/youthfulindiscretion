package player.http;

public class RemoteSegmentResponseObject {
	public final String url;
	public final String character;
	public final String text;
	
	public RemoteSegmentResponseObject(String url, String character, String text) {
		this.url = url;
		this.character = character;
		this.text = text;
	}

	public String getUrl() { return url; }
	public String getCharacter() { return character; }
	public String getText() { return text; }
}
