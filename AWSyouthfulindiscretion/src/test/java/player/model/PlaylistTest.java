package player.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import player.db.PlaylistDAO;
import player.model.Playlist;
import player.model.VideoSegment;

public class PlaylistTest {

	@Test
	public void test() {
		try {
			PlaylistDAO pdao = new PlaylistDAO();
			Playlist add = new Playlist();		
			pdao.addPlaylist(add);
			Playlist ret = pdao.getPlaylist(add.id.toString());
			assertEquals(add.id, ret.id);
			System.out.println("playlist: " + ret.id.toString());
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

	@Test
	public void testAll() {
		try {
			PlaylistDAO pdao = new PlaylistDAO();
			List<Playlist> retList = pdao.getAllPlaylists();
			for(Playlist p : retList) {
				System.out.println("playlist: " + p.id.toString());
				for (VideoSegment v : p.videoSegments) {
					System.out.println("vid seg: " + v.id.toString());
					System.out.println("\t actor: " + v.actor);
					System.out.println("\t phrase: " + v.phrase);
					System.out.println("\t url: " + v.url);
				}
			}
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

}
