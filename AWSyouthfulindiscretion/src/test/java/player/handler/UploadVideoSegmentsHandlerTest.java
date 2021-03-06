package player.handler;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Base64;

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

import player.db.VideoSegmentDAO;
import player.http.CreateVideoSegmentRequest;
import player.http.CreateVideoSegmentResponse;
import player.http.SiteCreateRequest;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class UploadVideoSegmentsHandlerTest {

    private final String CONTENT_TYPE = "ogg";
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
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(CONTENT_TYPE);
    }

    private Context createContext() {
        TestContext ctx = new TestContext();
        ctx.setFunctionName("uploadVideoSegments");

        return ctx;
    }

    @Test
    public void testUploadVideoSegmentsHandler() {
    	VideoSegmentDAO dao = new VideoSegmentDAO();
        UploadVideoSegmentsHandler handler = new UploadVideoSegmentsHandler();
        Context ctx = createContext();
        
        int x = (int)(Math.random()*(1000));
        byte[] contents = {1,2,3,4,5};
        String encodedContents = Base64.getEncoder().encodeToString(contents);
        CreateVideoSegmentRequest request = new CreateVideoSegmentRequest("File Name: " + x, "actor", "phrase", encodedContents);
        CreateVideoSegmentResponse output = handler.handleRequest(request, ctx);
        Assert.assertEquals(200, output.statusCode);      
        Assert.assertEquals("https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/File Name: " + x, output.vs.url);
        
        request = new CreateVideoSegmentRequest("File Name: " + x, "actor", "phrase", encodedContents);
        output = handler.handleRequest(request, ctx);
        Assert.assertEquals(409, output.statusCode);
        try {
        	dao.deleteVideoSegment("https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/File Name: " + x);
        }
        catch (Exception e) {
        	fail("test failed: " + e.getMessage());
        }
        
    }
}
