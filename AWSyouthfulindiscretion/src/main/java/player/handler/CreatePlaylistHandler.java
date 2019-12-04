package player.handler;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import player.model.Playlist;
import player.http.PlaylistRequest;
import player.http.CreatePlaylistResponse;
import player.db.PlaylistDAO;

public class CreatePlaylistHandler implements RequestHandler<PlaylistRequest, CreatePlaylistResponse>{

	LambdaLogger logger;
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createPlaylist(String name) throws Exception {
		if (logger != null) { logger.log("in createPlaylist"); }
		PlaylistDAO dao = new PlaylistDAO();
		
		Playlist playlist = new Playlist(UUID.fromString(name));
		logger.log("playlist: " + playlist.id.toString());
		return dao.addPlaylist(playlist);
	}
	
	@Override 
	public CreatePlaylistResponse handleRequest(PlaylistRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		CreatePlaylistResponse response;
		try {
			
			if (createPlaylist(req.playlistName)) {
				response = new CreatePlaylistResponse(req.playlistName);
			} else {
				response = new CreatePlaylistResponse(req.playlistName, 422);
			}
		} catch (Exception e) {
			response = new CreatePlaylistResponse("Unable to create playlist (" + e.getMessage() + ")", 400);
		}

		return response;
	}
	
}
