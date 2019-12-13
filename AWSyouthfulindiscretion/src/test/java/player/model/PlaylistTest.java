package player.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import player.db.PlaylistDAO;
import player.model.Playlist;
import player.model.VideoSegment;

@RunWith(JUnit4.class)
public class PlaylistTest {

	@Test
	public void test() {
		try {
			PlaylistDAO pdao = new PlaylistDAO();
			Playlist add = new Playlist();		
			pdao.addPlaylist(add);
			Playlist ret = pdao.getPlaylist(add.id.toString());
			assertEquals(add.id, ret.id);
			Playlist add2 = new Playlist();
			assertEquals(add.equals(add2), false);
			assertEquals(add.equals(null), false);
			assertEquals(add.equals(new Object()), false);
			pdao.deletePlaylist(add);
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

}
