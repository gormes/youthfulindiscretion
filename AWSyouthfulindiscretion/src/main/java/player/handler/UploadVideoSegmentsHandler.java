package player.handler;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.Statement;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import player.db.DatabaseUtil;
import player.db.VideoSegmentDAO;
import player.http.CreateVideoSegmentRequest;
import player.http.CreateVideoSegmentResponse;
import player.model.VideoSegment;

public class UploadVideoSegmentsHandler implements RequestHandler<CreateVideoSegmentRequest, CreateVideoSegmentResponse> {

    private AmazonS3 s3 = null;
    private VideoSegment addedVS = null;
    public UploadVideoSegmentsHandler() {
    }
    
    boolean createVideoSegment(String fileName, String actor, String phrase,  byte[]  contents) throws Exception{
    	VideoSegmentDAO dao = new VideoSegmentDAO();

    	//check if present
    	VideoSegment exist = dao.getVideoSegment(fileName);
    	VideoSegment vs = new VideoSegment(fileName, actor, phrase);
    	
    	if (exist == null) {
    		//add to the bucket
        	if (s3 == null) {
    			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
    		}
        	ByteArrayInputStream bais = new ByteArrayInputStream(contents);
        	ObjectMetadata omd = new ObjectMetadata();
        	omd.setContentLength(contents.length);
        		
        	PutObjectResult res = s3.putObject(new PutObjectRequest("3733youthfulindiscretion", "videoSegments/" + fileName, bais, omd));
        	
        	return dao.addVideoSegment("https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/" + fileName, vs);
        	
    	} else {
    		throw new Exception("Unable to add duplicate Video Segment");
    	}
        
    }		

    @Override
	public CreateVideoSegmentResponse handleRequest(CreateVideoSegmentRequest input, Context context) {
		CreateVideoSegmentResponse response;
	
		try {
			if(createVideoSegment(input.fileName, input.actor, input.phrase, input.contents)) {
				VideoSegmentDAO dao = new VideoSegmentDAO();
				response = new CreateVideoSegmentResponse(dao.getVideoSegment(input.fileName), 201);
			} else {
				response = new CreateVideoSegmentResponse(409, "Uploaded duplicate segment file: " + input.fileName);
			}
		} catch (Exception e) {
			response = new CreateVideoSegmentResponse(400, "Unable to create video segment file: " + input.fileName + "(" + e.getMessage() + ")");
		}
		// TODO Auto-generated method stub
		return response;
	}
	
}