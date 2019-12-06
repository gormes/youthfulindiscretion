package player.handler;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

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
    private byte[] contents = null;
    
    public UploadVideoSegmentsHandler() {
    }
    
    boolean createVideoSegment(String fileName, String actor, String phrase,  byte[]  contents) throws Exception{
    	VideoSegmentDAO dao = new VideoSegmentDAO();

    	//check if present
    	VideoSegment exist = dao.getVideoSegmentFromName(fileName);
    	
    	if (exist == null) {
    		//add to the bucket
        	if (s3 == null) {
    			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
    		}
        	ByteArrayInputStream bais = new ByteArrayInputStream(contents);
        	ObjectMetadata omd = new ObjectMetadata();
        	omd.setContentLength(contents.length);
        		
        	PutObjectResult res = s3.putObject(new PutObjectRequest("3733youthfulindiscretion", "videoSegments/" + fileName, bais, omd));
        	
        	VideoSegment vs = new VideoSegment(actor, phrase, "https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/" + fileName);
        	return dao.addVideoSegment(vs);
    	} else {
    		return false;
    	}
        
    }		

    @Override
	public CreateVideoSegmentResponse handleRequest(CreateVideoSegmentRequest input, Context context) {
		CreateVideoSegmentResponse response;
		contents = Base64.getDecoder().decode(input.encodedContents);
		
		try {
			if(createVideoSegment(input.fileName, input.actor, input.phrase, contents)) {
				VideoSegmentDAO dao = new VideoSegmentDAO();
				response = new CreateVideoSegmentResponse(dao.getVideoSegmentFromName(input.fileName), 200);
			} else {
				response = new CreateVideoSegmentResponse(409, "Uploaded duplicate segment file: " + input.fileName);
			}
		} catch (Exception e) {
			response = new CreateVideoSegmentResponse(400, "Unable to create video segment file: " + input.fileName + "(" + e.getMessage() + ")");
		}
		return response;
	}
	
}