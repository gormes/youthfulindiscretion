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
	public DeleteVideoSegmentResponse handleRequest(DeleteVideoSegmentRequest req, Context context) {
		DeleteVideoSegmentResponse response= null;

		VideoSegmentDAO dao= new VideoSegmentDAO();
		
		//Where would i get the id/name of video segment -> where is the RequestHandler 
		//(String actor, String phrase, String url, UUID id, boolean marked)
		
		try {
			if(dao.deleteVideoSegment(req.s3BucketURL)) {
				//it passes 
				//same here- where does it go
				response= new DeleteVideoSegmentResponse(req.s3BucketURL,200);
			}
			else {
				response = new DeleteVideoSegmentResponse(req.s3BucketURL, 409, "Unable to delete video segment " + req.s3BucketURL);
			}
		}
		catch (Exception e) {
			response = new DeleteVideoSegmentResponse(req.s3BucketURL, 403, "Unable to delete video segment: " + req.s3BucketURL + "(" + e.getMessage() + ")");
		}
		
		return response;
	}
}