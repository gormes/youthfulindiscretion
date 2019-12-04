package player.handler;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.google.gson.Gson;

import player.handler.CreatePlaylistHandler;
import player.http.PlaylistRequest;
import player.http.CreatePlaylistResponse;

public class CreatePlaylistHandlerTest extends LambdaTest{

	void testSuccessInput(String incoming) throws IOException {
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
		PlaylistRequest req = new Gson().fromJson(incoming, PlaylistRequest.class);
       
		CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	PlaylistRequest req = new Gson().fromJson(incoming, PlaylistRequest.class);

    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }
    
    @Test
    public void testShouldBeOk() {
    	PlaylistRequest ccr = new PlaylistRequest();
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid: " + ioe.getMessage());
        }
    }
}
