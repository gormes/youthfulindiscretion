package player.handler;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import player.handler.CreatePlaylistHandler;
import player.handler.DeletePlaylistHandler;
import player.http.CreatePlaylistResponse;
import player.http.DeletePlaylistResponse;
import player.http.PlaylistRequest;

public class DeletePlaylistHandlerTest extends LambdaTest {

	@Test
    public void testCreateAndDeletePlaylist() {
        PlaylistRequest cpr = new PlaylistRequest();
        
        CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(cpr, createContext("create"));
        Assert.assertEquals(cpr.playlistName, resp.response);
        
        // now delete
        PlaylistRequest dpr = new PlaylistRequest(cpr.playlistName);
        DeletePlaylistResponse d_resp = new DeletePlaylistHandler().handleRequest(dpr, createContext("delete"));
        Assert.assertEquals(dpr.playlistName, d_resp.response);
        
        // try again and this should fail
        d_resp = new DeletePlaylistHandler().handleRequest(dpr, createContext("delete"));
        Assert.assertEquals(409, d_resp.statusCode);
    }
    

}
