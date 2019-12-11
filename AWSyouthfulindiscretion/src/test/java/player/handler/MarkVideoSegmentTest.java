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

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import player.http.CreateVideoSegmentRequest;
import player.http.CreateVideoSegmentResponse;
import player.http.VideoSegmentMarkRequest;
import player.http.VideoSegmentMarkResponse;
import player.model.VideoSegment;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class MarkVideoSegmentTest {

    private final String CONTENT_TYPE = "image/jpeg";
    private S3Event event;

    @Mock
    private AmazonS3 s3Client;
    @Mock
    private S3Object s3Object;

    @Captor
    private ArgumentCaptor<GetObjectRequest> getObjectRequest;

    @Before
    public void setUp() throws IOException {
        event = TestUtils.parse("/s3-event.put.json", S3Event.class);

        // TODO: customize your mock logic for s3 client
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(CONTENT_TYPE);
        //when(s3Object.getObjectMetadata()).thenReturn(objectMetadata);
        //when(s3Client.getObject(getObjectRequest.capture())).thenReturn(s3Object);
    }
    
    
    private Context createContext() {
        TestContext ctx = new TestContext();
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testMarkVideoSegment() {
    	try {
	        MarkVideoSegmentHandler handler = new MarkVideoSegmentHandler();
	        Context ctx = createContext();
	        
	        int x = (int)(Math.random()*(1000));
	        
	        UploadVideoSegmentsHandler handlerVS = new UploadVideoSegmentsHandler();
	        CreateVideoSegmentRequest requestVS = new CreateVideoSegmentRequest("Mark Test: " + x, "actor", "phrase", "contents");
	        CreateVideoSegmentResponse responseVS = handlerVS.handleRequest(requestVS, ctx);
	        VideoSegment vs = responseVS.vs;
	        	
	        VideoSegmentMarkRequest request = new VideoSegmentMarkRequest("https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/Mark Test: " + x, true);
	        VideoSegmentMarkResponse output = handler.handleRequest(request, ctx);
	        Assert.assertEquals(200, output.statusCode);
	        output = handler.handleRequest(request, ctx);
	        Assert.assertEquals(400, output.statusCode);
    	}
    	catch (Exception e) {
    		fail("test failed: " + e.getMessage());
    	}
    }
}
