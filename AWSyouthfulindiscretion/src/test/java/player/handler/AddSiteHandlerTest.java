package player.handler;

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

import player.http.SiteCreateRequest;
import player.http.SiteCreateResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddSiteHandlerTest {

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
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(CONTENT_TYPE);
    }

    private Context createContext() {
        TestContext ctx = new TestContext();
        ctx.setFunctionName("addSiteRequest");
        return ctx;
    }

    @Test
    public void testAddSiteHandler() {
        AddSiteHandler handler = new AddSiteHandler();
        Context ctx = createContext();

        int x = (int)(Math.random()*(1000));
        SiteCreateRequest request = new SiteCreateRequest("Test: " + x);
        SiteCreateResponse output = handler.handleRequest(request, ctx);
        Assert.assertEquals("Test: " + x, output.url);
        Assert.assertEquals(200, output.statusCode);
        
        request = new SiteCreateRequest("Test: " + x);
        output = handler.handleRequest(request, ctx);
        Assert.assertEquals(409, output.statusCode);
    }

    @Test
    public void testAddSiteHandlerFail() {
        AddSiteHandler handler = new AddSiteHandler();
        Context ctx = createContext();

        SiteCreateRequest request = new SiteCreateRequest();
        SiteCreateResponse output = handler.handleRequest(request, ctx);
        
        output = handler.handleRequest(request, ctx);
        Assert.assertEquals(400, output.statusCode);
    }
}
