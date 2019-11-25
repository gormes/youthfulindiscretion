package player.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import player.db.PlaylistDAO;
import player.http.AllPlaylistsResponse;
import player.model.Playlist;

public class ListAllPlaylistsHandler implements RequestHandler<S3Event, AllPlaylistsResponse> {

    private AmazonS3 s3 = null;

    public ListAllPlaylistsHandler() {}

    // Test purpose only.
    ListAllPlaylistsHandler(AmazonS3 s3) {
        this.s3 = s3;
    }
    
    List<Playlist> listAllPlaylists() throws Exception{
    	PlaylistDAO dao = new PlaylistDAO();
    	
    	return dao.getAllPlaylists();
    }

    @Override
    public AllPlaylistsResponse handleRequest(S3Event event, Context context) {
    	context.getLogger().log("Received event: " + event);

        AllPlaylistsResponse response;
        try {
        	List<Playlist> list = listAllPlaylists();
        	response = new AllPlaylistsResponse(list, 200);
        } catch (Exception e) {
        	response = new AllPlaylistsResponse(404, e.getMessage());
        }
        return response;
    }
}