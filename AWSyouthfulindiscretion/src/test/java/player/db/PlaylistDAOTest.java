package player.db;

import static org.junit.Assert.*;
import org.junit.Assert;

import java.util.ArrayList;

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
			VideoSegment vs1 = new VideoSegment("testActor1", "testPhrase1", "testURL1");
			VideoSegment vs2 = new VideoSegment("testActor1", "testPhrase2", "testURL2");
			Playlist p = new Playlist();
			dao.addPlaylist(p);
			dao.appendToPlaylist(vs1.url, p.id.toString());
			
			assertTrue(dao.findVideoSegment(p.id.toString(), vs1.url));
			assertFalse(dao.findVideoSegment(p.id.toString(), vs2.url));
			
			dao.deleteFromPlaylist(p.id.toString(),vs1.url);
			
			dao.deletePlaylist(p);
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

}
