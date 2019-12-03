package player.http;

public class CreateVideoSegmentRequest {
	public String fileName;
	public String actor;
	public String phrase;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName (String fileName) {
		this.fileName = fileName;
	}
	
	public String actor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	
	public String phrase() {
		return phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	public CreateVideoSegmentRequest() {
		
	}
	
	public CreateVideoSegmentRequest(String fileName, String actor, String phrase) {
		this.fileName = fileName;
		this.actor = actor;
		this.phrase = phrase;
	}
	
	public String toString() {
		return "CreateVideoSegment(" + fileName + "," + actor + "," + phrase + ")"; 
	}
}
