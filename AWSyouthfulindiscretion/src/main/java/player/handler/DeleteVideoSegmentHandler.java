package player.handler;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import player.db.VideoSegmentDAO;
import player.http.DeletePlaylistResponse;
import player.http.DeleteVideoSegmentRequest;
import player.http.DeleteVideoSegmentResponse;
import player.model.VideoSegment;

public class DeleteVideoSegmentHandler implements RequestHandler<DeleteVideoSegmentRequest, DeleteVideoSegmentResponse> {

	public DeleteVideoSegmentHandler() {}


	@Override
	public DeleteVideoSegmentResponse handleRequest(DeleteVideoSegmentRequest event, Context context) {
		System.out.print("anything");
		DeleteVideoSegmentResponse response= null;

		VideoSegmentDAO dao= new VideoSegmentDAO();
		
		//Where would i get the id/name of video segment -> where is the RequestHandler 
		//(String actor, String phrase, String url, UUID id, boolean marked)
		VideoSegment vs= new VideoSegment("","","",event.vsId);
		
		try {
			if(dao.deleteVideoSegment(vs)) {
				//it passes 
				//same here- where does it go
				response= new DeleteVideoSegmentResponse(event.vsId.toString(),200);
			}
			else {
				response = new DeleteVideoSegmentResponse(event.vsId.toString(), 409, "Unable to delete video segment.");
			}
		}
		catch (Exception e) {
			response = new DeleteVideoSegmentResponse(event.vsId.toString(), 403, "Unable to delete video segment: " + event.vsId + "(" + e.getMessage() + ")");
		}
		
		return response;
	}
}