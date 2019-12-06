package player.handler;

import java.io.ByteArrayInputStream;

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

import player.db.SiteDAO;
import player.http.SiteCreateRequest;
import player.http.SiteCreateResponse;
import player.model.Site;
import player.model.VideoSegment;

public class AddSiteHandler implements RequestHandler<SiteCreateRequest, SiteCreateResponse> {
	
	public AddSiteHandler() {}

    boolean addSite(String url) throws Exception{
    	try {
	    	SiteDAO dao = new SiteDAO();
	
	    	Site exist = dao.getSite(url);
	    	
	    	if (exist == null) {
	    		dao.addSite(url);
	    		return true;
	    	} else {
	    		return false;
	    	}
    	}
    	catch (Exception e) {
    		throw e;
    	}
    }
    
    @Override
    public SiteCreateResponse handleRequest(SiteCreateRequest request, Context context){
    	SiteCreateResponse response;
    	try {
    		if(addSite(request.url)) {
    			response = new SiteCreateResponse(request.url, 200);
    		} else {
    			response = new SiteCreateResponse(409, "Duplicate site");
    		}
    		
    	} catch (Exception e) {
    		response = new SiteCreateResponse(400, "Unable to add site to the list");
    	}
    	return response;
    }
}