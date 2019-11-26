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