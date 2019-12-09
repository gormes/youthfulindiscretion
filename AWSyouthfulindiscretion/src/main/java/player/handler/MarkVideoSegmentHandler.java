package player.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import player.db.VideoSegmentDAO;
import player.http.VideoSegmentMarkResponse;
import player.http.VideoSegmentMarkRequest;
import player.model.VideoSegment;

public class MarkVideoSegmentHandler implements RequestHandler<VideoSegmentMarkRequest, VideoSegmentMarkResponse> {
	
    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    public MarkVideoSegmentHandler() {}

    // Test purpose only.
    MarkVideoSegmentHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public VideoSegmentMarkResponse handleRequest(VideoSegmentMarkRequest request, Context context) {
        VideoSegmentMarkResponse response;
        VideoSegmentDAO dao = new VideoSegmentDAO();
        VideoSegment vs;
		try {
			vs = dao.getVideoSegmentFromURL(request.getUrl());
			if(vs.marked == request.getMarked()) {
				response = new VideoSegmentMarkResponse(400, "Invalid Input: this segment is already marked/unmarked");
			} else {
				vs.marked = request.getMarked();
				dao.markVideoSegment(vs);
				response = new VideoSegmentMarkResponse(200, vs.toString());
			}
		} catch (Exception e) {
			response = new VideoSegmentMarkResponse(409, "Unable to mark video segments");
		}
    	return response;
    }
    
}