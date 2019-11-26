package player.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import player.db.SiteDAO;
import player.db.VideoSegmentDAO;
import player.http.AllSitesResponse;
import player.http.AllVideoSegmentsResponse;
import player.model.Site;
import player.model.VideoSegment;

public class ListAllSites implements RequestHandler<S3Event, AllSitesResponse> {

    private AmazonS3 s3 = null;

    public ListAllSites() {}

    // Test purpose only.
    ListAllSites(AmazonS3 s3) {
        this.s3 = s3;
    }

    List<Site> listAllSites() throws Exception{
    	SiteDAO dao = new SiteDAO();
    	
    	return dao.getAllSites();
    }
    
    @Override
    public AllSitesResponse handleRequest(S3Event event, Context context) {
        context.getLogger().log("Received event: " + event);

        AllSitesResponse response;
        try {
        	List<Site> list = listAllSites();
        	response = new AllSitesResponse(list, 200);
        } catch (Exception e) {
        	response = new AllSitesResponse(404, e.getMessage());
        }
        return response;
    }
}