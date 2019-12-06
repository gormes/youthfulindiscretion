package player.handler;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import player.db.VideoSegmentDAO;
import player.http.AllVideoSegmentsResponse;
import player.http.CreatePlaylistResponse;
import player.http.CreateVideoSegmentRequest;
import player.http.CreateVideoSegmentResponse;
import player.http.DeletePlaylistResponse;
import player.http.DeleteVideoSegmentRequest;
import player.http.DeleteVideoSegmentResponse;
import player.http.PlaylistRequest;
import player.model.Playlist;
import player.model.VideoSegment;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteVideoSegmentHandlerTest extends LambdaTest{
	
	private Context createContext() {
		TestContext ctx = new TestContext();
		ctx.setFunctionName("deleteVideoSegment");
		return ctx;
	}
	
	@Test
	public void testDeleteVideoSegmentHandler() {
		Context ctx = createContext();
		
		DeleteVideoSegmentHandler deleteHandler = new DeleteVideoSegmentHandler();
		UploadVideoSegmentsHandler createHandler= new UploadVideoSegmentsHandler();
		


		// CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(cpr, createContext("create"));
		// Assert.assertEquals(cpr.vsId, resp.response);
		VideoSegmentDAO dao= new VideoSegmentDAO();
		VideoSegment vs;
		try {
			String name = "testName";
			CreateVideoSegmentRequest cvsr = new CreateVideoSegmentRequest(name, "testActor", "testPhrase", "Mi43MTgyODE4Mjg=");
			CreateVideoSegmentResponse c_resp = createHandler.handleRequest(cvsr, ctx);
			
			vs = dao.getVideoSegmentFromName(name);

			DeleteVideoSegmentRequest dvsr = new DeleteVideoSegmentRequest(vs.url);
			DeleteVideoSegmentResponse d_resp = deleteHandler.handleRequest(dvsr, ctx);
			
			//should pass
			Assert.assertEquals(dvsr.s3BucketURL, d_resp.response);
			Assert.assertEquals(200, d_resp.statusCode);
			
			//should fail 
			 d_resp = new DeleteVideoSegmentHandler().handleRequest(dvsr, ctx);
		     Assert.assertEquals(409, d_resp.statusCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("test failed: " + e.getMessage()); 
		}

	}
}
