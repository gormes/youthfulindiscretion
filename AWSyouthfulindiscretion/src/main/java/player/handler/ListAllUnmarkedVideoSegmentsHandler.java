package player.handler;

import java.util.List;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;

import player.db.VideoSegmentDAO;
import player.http.AllUnmarkedVideoSegmentsResponse;
import player.model.VideoSegment;

public class ListAllUnmarkedVideoSegmentsHandler implements RequestHandler<S3Event, AllUnmarkedVideoSegmentsResponse>{

    private AmazonS3 s3 = null;

    public ListAllUnmarkedVideoSegmentsHandler() {}

    // Test purpose only.
    ListAllUnmarkedVideoSegmentsHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    List<VideoSegment> listAllUnmarkedVideoSegments() throws Exception{
    	VideoSegmentDAO dao = new VideoSegmentDAO();
    	
    	return dao.getAllUnmarkedVideoSegments();
    }
    
    @Override
    public AllUnmarkedVideoSegmentsResponse handleRequest(S3Event event, Context context) {
        context.getLogger().log("Received event: " + event);

        AllUnmarkedVideoSegmentsResponse response;
        try {
        	List<VideoSegment> list = listAllUnmarkedVideoSegments();
        	response = new AllUnmarkedVideoSegmentsResponse(list, 200);
        } catch (Exception e) {
        	response = new AllUnmarkedVideoSegmentsResponse(404, e.getMessage());
        }
        return response;
    }
	
}
