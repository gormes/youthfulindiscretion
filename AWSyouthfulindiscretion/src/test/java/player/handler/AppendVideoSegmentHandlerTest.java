package player.handler;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import player.db.PlaylistDAO;
import player.db.VideoSegmentDAO;
import player.http.AppendVideoSegmentRequest;
import player.http.AppendVideoSegmentResponse;
import player.model.Playlist;
import player.model.VideoSegment;

public class AppendVideoSegmentHandlerTest extends LambdaTest {

	@Test
	public void test() {
		PlaylistDAO pdao = new PlaylistDAO();
		VideoSegmentDAO vdao = new VideoSegmentDAO();
		Playlist p = new Playlist();
		VideoSegment vs = new VideoSegment("Kirk", "are you","https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/areyou_kirk.ogg");
		try {
			pdao.addPlaylist(p);
			
			AppendVideoSegmentHandler handler = new AppendVideoSegmentHandler();
			AppendVideoSegmentRequest request = new AppendVideoSegmentRequest(vs.url, p.id.toString());
			AppendVideoSegmentResponse response;
			response = handler.handleRequest(request, createContext("append"));
			Assert.assertEquals(request.plid, response.response);
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}
	


}
