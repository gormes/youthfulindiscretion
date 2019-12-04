package player.handler;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import player.http.PlaylistRequest;
import player.model.Playlist;
import player.db.PlaylistDAO;
import player.http.DeletePlaylistResponse;

public class DeletePlaylistHandler implements RequestHandler<PlaylistRequest, DeletePlaylistResponse> {

	public LambdaLogger logger = null;

	@Override
	public DeletePlaylistResponse handleRequest(PlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		DeletePlaylistResponse response = null;
		logger.log(req.toString());

		PlaylistDAO dao = new PlaylistDAO();

		// See how awkward it is to call delete with an object, when you only
		// have one part of its information?
		Playlist playlist = new Playlist(UUID.fromString(req.playlistName));
		try {
			if (dao.deletePlaylist(playlist)) {
				response = new DeletePlaylistResponse(req.playlistName, 200);
			} else {
				response = new DeletePlaylistResponse(req.playlistName, 409, "Unable to delete playlist.");
			}
		} catch (Exception e) {
			response = new DeletePlaylistResponse(req.playlistName, 403, "Unable to delete playlist: " + req.playlistName + "(" + e.getMessage() + ")");
		}

		return response;
	}
}
