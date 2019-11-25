package player.handler;

import static org.mockito.Mockito.when;

import java.awt.List;
import java.util.*;
import java.io.IOException;
import player.db.*;
import player.handler.ListAllVideoSegmentsHandler;
import player.model.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
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

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListAllVideoSegmentsTest {

    private final String CONTENT_TYPE = "mp4";
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
        when(s3Object.getObjectMetadata()).thenReturn(objectMetadata);
        when(s3Client.getObject(getObjectRequest.capture())).thenReturn(s3Object);
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("listAllVideoSegments");

        return ctx;
    }

    @Test
    public void testListAllVideoSegments() {
        ListAllVideoSegmentsHandler handler = new ListAllVideoSegmentsHandler(s3Client);
        Context ctx = createContext();

        String output = handler.handleRequest(event, ctx);
        
        try {
        	handler = new ListAllVideoSegmentsHandler(AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build());
        	java.util.List<VideoSegment> resultList = handler.listAllVideoSegmentsS3();
        	for(int i = 0; i < resultList.size(); i ++) {
        		System.out.println
        		("Result" + i + ": " + resultList.get(i).actor + ", " + resultList.get(i).phrase + ", " +  resultList.get(i).url);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}

        System.out.print(output);
        Assert.assertEquals(CONTENT_TYPE, output);
    }
}
