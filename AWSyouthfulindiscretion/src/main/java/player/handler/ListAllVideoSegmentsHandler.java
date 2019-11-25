package player.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import player.model.*;
import player.db.*;
import player.http.AllVideoSegmentsResponse;

public class ListAllVideoSegmentsHandler implements RequestHandler<S3Event, AllVideoSegmentsResponse> {
   
	private AmazonS3 s3 = null;

    public ListAllVideoSegmentsHandler() { }

    // Test purpose only.
   ListAllVideoSegmentsHandler(AmazonS3 s3) {
	   this.s3 = s3;
    }

    List<VideoSegment> listAllVideoSegments() throws Exception{
    	VideoSegmentDAO dao = new VideoSegmentDAO();
    	
    	return dao.getAllVideoSegments();
    }
    
    List<VideoSegment> listAllVideoSegmentsS3() throws Exception {
    	if(s3 == null) {
    		s3 = s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
    	}
    	
    	ArrayList<VideoSegment> videoSeg = new ArrayList<>();
    	ListObjectsV2Request listAllObjRequest = new ListObjectsV2Request().withBucketName("3733youthfulindiscretion").withPrefix("videoSegments");
    	
    	ListObjectsV2Result result = s3.listObjectsV2(listAllObjRequest);
  
    	
    	List<S3ObjectSummary> objects =  result.getObjectSummaries();
    	
		for (S3ObjectSummary os: objects) {
		      String name = os.getKey();
		      
		      if (name.endsWith("/")) { continue; }
				
		      S3Object obj = s3.getObject("3733youthfulindiscretion", name);
		    	
		    	try (S3ObjectInputStream constantStream = obj.getObjectContent()) {
					Scanner sc = new Scanner(constantStream);
					String val = sc.nextLine();
					sc.close();
					
					int postSlash = name.indexOf('/');
					videoSeg.add(new VideoSegment("Actor", "Phrase", "https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/" + name));
				} catch (Exception e) {
				}
		    }
			return videoSeg;
    }
    
    
    @Override
    public AllVideoSegmentsResponse handleRequest(S3Event event, Context context) {
        context.getLogger().log("Received event: " + event);

        AllVideoSegmentsResponse response;
        try {
        	List<VideoSegment> list = listAllVideoSegments();
        	response = new AllVideoSegmentsResponse(list, 200);
        } catch (Exception e) {
        	response = new AllVideoSegmentsResponse(404, e.getMessage());
        }
        return response;
    }
}