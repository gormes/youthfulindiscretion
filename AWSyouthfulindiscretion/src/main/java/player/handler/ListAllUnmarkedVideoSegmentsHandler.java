package player.handler;

import java.util.ArrayList;
import java.util.List;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;

import player.db.VideoSegmentDAO;
import player.http.AllUnmarkedVideoSegmentsResponse;
import player.model.VideoSegment;

import player.http.RemoteSegmentResponseObject;

public class ListAllUnmarkedVideoSegmentsHandler implements RequestHandler<Object, AllUnmarkedVideoSegmentsResponse>{

    private AmazonS3 s3 = null;

    public ListAllUnmarkedVideoSegmentsHandler() {}

    // Test purpose only.
    ListAllUnmarkedVideoSegmentsHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    List<RemoteSegmentResponseObject> listAllUnmarkedVideoSegments() throws Exception{
    	List<RemoteSegmentResponseObject> ret = new ArrayList<RemoteSegmentResponseObject>();
    	VideoSegmentDAO dao = new VideoSegmentDAO();
    	List<VideoSegment> tmp = dao.getAllUnmarkedVideoSegments();
    	for(VideoSegment vs : tmp) {
    		ret.add(new RemoteSegmentResponseObject(vs.url, vs.actor, vs.phrase));
    	}
    	return ret;
    }
    
    @Override
    public AllUnmarkedVideoSegmentsResponse handleRequest(Object input, Context context) {
        context.getLogger().log("Loading Java Lambda handler to list all remote segments");

        AllUnmarkedVideoSegmentsResponse response;
        try {
        	List<RemoteSegmentResponseObject> list = listAllUnmarkedVideoSegments();
        	response = new AllUnmarkedVideoSegmentsResponse(list, 200);
        } catch (Exception e) {
        	response = new AllUnmarkedVideoSegmentsResponse(404, e.getMessage());
        }
        return response;
    }
	
}
