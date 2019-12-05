package player.http;

import java.util.UUID;

public class DeleteVideoSegmentRequest {
	public final UUID vsId;

	public DeleteVideoSegmentRequest() {
		vsId = UUID.randomUUID();
	}

	public String toString() {
		return "DeleteVideoSegment(" + vsId + ")";
	}
	
	public DeleteVideoSegmentRequest(UUID id) {
		vsId= id;
	}


}
