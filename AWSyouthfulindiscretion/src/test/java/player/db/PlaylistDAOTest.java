package player.db;

import static org.junit.Assert.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

import player.model.Playlist;
import player.model.VideoSegment;

public class PlaylistDAOTest {

	@Test
	public void testCreateAppend() {
		PlaylistDAO dao = new PlaylistDAO();
		
		try {
			VideoSegment vs = new VideoSegment("testActor","testPhrase","testURL");
			Playlist p = new Playlist();
			boolean response = dao.addPlaylist(p);
			
			assertEquals(true, response);
			
			response = dao.appendToPlaylist(vs.url, p.id.toString());
			
			dao.deletePlaylist(p);
			
			assertEquals(true, response);
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}
	
	@Test
	public void testFindandDeleteFrom() {
		PlaylistDAO dao = new PlaylistDAO();
		
		try {
			VideoSegment vs1 = new VideoSegment("testActor1", "testPhrase1", "https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/oh_mccoy.ogg");
			VideoSegment vs2 = new VideoSegment("testActor2", "testPhrase2", "https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/captain_mccoy.ogg");
			Playlist p = new Playlist();
			dao.addPlaylist(p);
			dao.appendToPlaylist(vs1.url, p.id.toString());
			
			assertTrue(dao.findVideoSegment(p.id.toString(), vs1.url));
			assertFalse(dao.findVideoSegment(p.id.toString(), vs2.url));
			dao.appendToPlaylist(vs2.url, p.id.toString());

			dao.deleteFromPlaylist(p.id.toString(),vs1.url);
			dao.deleteFromPlaylist(p.id.toString(),vs2.url);
			
			assertEquals(p, dao.getPlaylist(p.id.toString()));
			
			dao.deletePlaylist(p);
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

}
