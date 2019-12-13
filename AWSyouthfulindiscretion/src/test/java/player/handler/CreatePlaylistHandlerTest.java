package player.handler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import com.google.gson.Gson;

import player.db.PlaylistDAO;
import player.handler.CreatePlaylistHandler;
import player.http.PlaylistRequest;
import player.model.Playlist;
import player.http.CreatePlaylistResponse;

public class CreatePlaylistHandlerTest extends LambdaTest{

	void testSuccessInput(String incoming) throws IOException {
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
		PlaylistRequest req = new Gson().fromJson(incoming, PlaylistRequest.class);
       
		CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
       
    }
    
    @Test
    public void testShouldBeOk() {
    	PlaylistDAO dao = new PlaylistDAO();
    	PlaylistRequest ccr = new PlaylistRequest();
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        	dao.deletePlaylist(new Playlist(UUID.fromString(ccr.playlistName)));
        } catch (IOException ioe) {
        	Assert.fail("Invalid: " + ioe.getMessage());
        } catch (Exception e) {
        	Assert.fail("test failed: " + e.getMessage());
    	}
    }
}
