package player.handler;

import static org.mockito.Mockito.when;

import java.io.IOException;

import static org.junit.Assert.*;
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

import player.model.Playlist;
import player.model.VideoSegment;
import player.http.AllPlaylistsResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListAllPlaylistsHandlerTest extends LambdaTest {

    private final String CONTENT_TYPE = "image/jpeg";
    private S3Event event;

    @Mock
    private AmazonS3 s3Client;
    @Mock
    private S3Event s3Event;

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

    @Test
    public void testListAllPlaylistsHandler() {
        ListAllPlaylistsHandler handler = new ListAllPlaylistsHandler();
        
        try {
        	handler = new ListAllPlaylistsHandler(AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build());
        	AllPlaylistsResponse resp = handler.handleRequest(s3Event,createContext("get"));
        	java.util.List<Playlist> resultList = resp.list;
        	for(int i = 0; i < resultList.size(); i ++) {
        		System.out.println("Result " + i + ": " + resultList.get(i).id + ", " + resultList.get(i).videoSegments.toString());
        	}
		} catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
    }
}
