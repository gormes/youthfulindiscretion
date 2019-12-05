package player.db;

import static org.junit.Assert.*;

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
			
			response = dao.appendToPlaylist(vs.id.toString(), p.id.toString());
			
			assertEquals(true, response);
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

}
