package player;

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
			Playlist ret = pdao.getPlaylist("1d3dc5c0-d87a-4cfb-b8d2-23a835d92533");
			System.out.println("playlist: " + ret.id.toString());
			for (VideoSegment v : ret.videoSegments) {
				System.out.println("vid seg: " + v.id.toString());
				System.out.println("\t actor: " + v.actor);
				System.out.println("\t phrase: " + v.phrase);
				System.out.println("\t url: " + v.url);
			}
			
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
