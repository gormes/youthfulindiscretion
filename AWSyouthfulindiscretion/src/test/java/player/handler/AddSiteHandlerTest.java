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

        // TODO: customize your mock logic for s3 client
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(CONTENT_TYPE);
       // when(s3Object.getObjectMetadata()).thenReturn(objectMetadata);
       // when(s3Client.getObject(getObjectRequest.capture())).thenReturn(s3Object);
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Add Site Request");

        return ctx;
    }

    @Test
    public void testAddSiteHandler() {
        AddSiteHandler handler = new AddSiteHandler();
        Context ctx = createContext();

        SiteCreateRequest request = new SiteCreateRequest("URL");
        SiteCreateResponse output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("URL", output.url);
    }
}
