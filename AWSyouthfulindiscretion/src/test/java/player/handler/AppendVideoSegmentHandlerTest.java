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
	@Test
	public void quickTest() {
		PlaylistDAO pdao = new PlaylistDAO();
		VideoSegmentDAO vdao = new VideoSegmentDAO();
		Playlist p = new Playlist();
		VideoSegment vs1 = new VideoSegment("Kirk", "are you","https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/areyou_kirk.ogg");
		VideoSegment vs2 = new VideoSegment("mmcoy", "captain", "https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/captain_mmcoy.ogg");
		try {
			pdao.addPlaylist(p);
			
			AppendVideoSegmentRequest request1 = new AppendVideoSegmentRequest(vs1.url, p.id.toString());
			AppendVideoSegmentRequest request2 = new AppendVideoSegmentRequest(vs2.url, p.id.toString());
			AppendVideoSegmentResponse response = new AppendVideoSegmentHandler().handleRequest(request1, createContext("append"));
			Assert.assertEquals(request1.plid, response.response);
			response = new AppendVideoSegmentHandler().handleRequest(request2, createContext("append"));
			Assert.assertEquals(request2.plid, response.response);
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}
	


}
