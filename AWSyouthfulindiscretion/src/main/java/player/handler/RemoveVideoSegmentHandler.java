package player.handler;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import player.db.PlaylistDAO;
import player.db.VideoSegmentDAO;
import player.http.RemoveVideoSegmentPlaylistRequest;
import player.http.RemoveVideoSegmentPlaylistResponse;
import player.model.Playlist;
import player.model.VideoSegment;

public class RemoveVideoSegmentHandler implements RequestHandler<RemoveVideoSegmentPlaylistRequest, RemoveVideoSegmentPlaylistResponse> {

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    public RemoveVideoSegmentHandler() {}

    // Test purpose only.
    RemoveVideoSegmentHandler(AmazonS3 s3) {
        this.s3 = s3;
    }


	@Override
	public RemoveVideoSegmentPlaylistResponse handleRequest(RemoveVideoSegmentPlaylistRequest input, Context context) {
		RemoveVideoSegmentPlaylistResponse response;
		PlaylistDAO dao = new PlaylistDAO();
		VideoSegmentDAO daoVS = new VideoSegmentDAO();
		String vsURL = input.getVideoSegmentURL();
		String pID = input.getPlaylistID();
		try {
			Playlist p = dao.getPlaylist(pID);
			VideoSegment vs = daoVS.getVideoSegmentFromURL(vsURL);
			if(dao.findVideoSegment(p, vs)) {
				dao.deleteFromPlaylist(p, vs);
				response = new RemoveVideoSegmentPlaylistResponse(200, vsURL + " Removed");
			} else {
				response = new RemoveVideoSegmentPlaylistResponse(400, "VideoSegment not in Playlist" + input.getVideoSegmentURL());

			}
		} catch (Exception e) {
			response = new RemoveVideoSegmentPlaylistResponse(409, e.getMessage());
		}
		return response;
	}
}