package player.handler;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import player.handler.ListAllUnmarkedVideoSegmentsHandler;
import player.http.AllUnmarkedVideoSegmentsResponse;
import player.model.VideoSegment;

public class AllUnmarkedVideoSegmentHandlerTest extends LambdaTest {
    private final String CONTENT_TYPE = "mp4";
    private S3Event event;

    @Mock
    private AmazonS3 s3Client;
    @Mock
    private S3Object s3Object;

    @Before
    public void setUp() throws IOException {
        event = TestUtils.parse("/s3-event.put.json", S3Event.class);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(CONTENT_TYPE);
        //when(s3Object.getObjectMetadata()).thenReturn(objectMetadata);
        //when(s3Client.getObject(getObjectRequest.capture())).thenReturn(s3Object);
    }
    
	@Test
	public void test() {
		AllUnmarkedVideoSegmentsResponse resp = new ListAllUnmarkedVideoSegmentsHandler().handleRequest(event, createContext("list"));
		for (VideoSegment vs : resp.segments) {
			System.out.println(vs);
		}
	}

}
