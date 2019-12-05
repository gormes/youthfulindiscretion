package player.http;

import java.util.UUID;

public class DeleteVideoSegmentRequest {
	public UUID vsId;

	public UUID getvsID() {return this.vsId;}
	
	public void setvsId(UUID id) {this.vsId=id;}
	
	public DeleteVideoSegmentRequest() {}

	public String toString() {
		return "DeleteVideoSegment(" + vsId + ")";
	}
	
	public DeleteVideoSegmentRequest(UUID id) {
		vsId= id;
	}


}
