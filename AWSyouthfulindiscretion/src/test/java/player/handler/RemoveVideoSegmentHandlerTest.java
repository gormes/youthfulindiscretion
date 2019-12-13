package player.handler;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

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

import player.db.PlaylistDAO;
import player.db.VideoSegmentDAO;
import player.http.AppendVideoSegmentRequest;
import player.http.AppendVideoSegmentResponse;
import player.http.CreatePlaylistResponse;
import player.http.CreateVideoSegmentRequest;
import player.http.CreateVideoSegmentResponse;
import player.http.PlaylistRequest;
import player.http.RemoveVideoSegmentPlaylistRequest;
import player.http.RemoveVideoSegmentPlaylistResponse;
import player.http.VideoSegmentMarkRequest;
import player.http.VideoSegmentMarkResponse;
import player.model.Playlist;
import player.model.VideoSegment;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class RemoveVideoSegmentHandlerTest {

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

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testRemoveVideoSegmentHandler() {
    	VideoSegmentDAO dao = new VideoSegmentDAO();
    	PlaylistDAO pdao = new PlaylistDAO();
        RemoveVideoSegmentHandler handler = new RemoveVideoSegmentHandler();
        Context ctx = createContext();
        
        int x = (int)(Math.random()*(1000));
        
        UploadVideoSegmentsHandler handlerVS = new UploadVideoSegmentsHandler();
        CreateVideoSegmentRequest requestVS = new CreateVideoSegmentRequest("Remove Test: " + x, "actor", "phrase", "contents");
        CreateVideoSegmentResponse responseVS = handlerVS.handleRequest(requestVS, ctx);
        VideoSegment vs = responseVS.vs;
        
        PlaylistRequest requestP = new PlaylistRequest();
        CreatePlaylistHandler handlerP = new CreatePlaylistHandler();
        CreatePlaylistResponse responseP = handlerP.handleRequest(requestP, ctx);
        
        AppendVideoSegmentRequest requestA = new AppendVideoSegmentRequest("https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/Remove Test: " + x, responseP.response);
        AppendVideoSegmentHandler handlerA = new AppendVideoSegmentHandler();
        AppendVideoSegmentResponse responseA = handlerA.handleRequest(requestA, ctx);
        
        RemoveVideoSegmentPlaylistRequest request = new RemoveVideoSegmentPlaylistRequest(responseP.response, "https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/Remove Test: " + x);
        RemoveVideoSegmentPlaylistResponse output = handler.handleRequest(request, ctx);
        Assert.assertEquals(200, output.statusCode);
        
        RemoveVideoSegmentPlaylistResponse outputErr = handler.handleRequest(request, ctx);
        Assert.assertEquals(400, outputErr.statusCode);
        
        try {
        	dao.deleteVideoSegment("https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/Remove Test: " + x);
        	pdao.deletePlaylist(new Playlist(UUID.fromString(requestP.playlistName)));
        }
        catch (Exception e) {
        	fail("test failed: " + e.getMessage());
        }
    }
}
