package player.http;

import java.util.UUID;

public class DeleteVideoSegmentRequest {
	public String s3BucketURL;

	public String getvsID() {return this.s3BucketURL;}
	
	public void setvsId(String url) {this.s3BucketURL=url;}
	
	public DeleteVideoSegmentRequest() {}

	public String toString() {
		return "DeleteVideoSegment(" + s3BucketURL + ")";
	}
	
	public DeleteVideoSegmentRequest(String url) {
		s3BucketURL = url;
	}


}
