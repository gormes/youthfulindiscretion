package player.http;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class InstantiateRequestResponse {
	
	@Test
	public void testInstantiate() {
		AllPlaylistsResponse allPlaylistNormal = new AllPlaylistsResponse(null, 200);
		AllPlaylistsResponse allPlaylistError = new AllPlaylistsResponse(400, "error message");
		
		AllSitesResponse allSitesNormal = new AllSitesResponse(null, 200);
		AllSitesResponse allSitesError = new AllSitesResponse(400, "error message");
		
		AllUnmarkedVideoSegmentsResponse allUnmarkedNormal = new AllUnmarkedVideoSegmentsResponse(null, 200);
		AllUnmarkedVideoSegmentsResponse allUnmarkedError = new AllUnmarkedVideoSegmentsResponse(400, "error message");
	
		AllVideoSegmentsResponse allVideoSegNormal = new AllVideoSegmentsResponse(null, 200);
		AllVideoSegmentsResponse allVideoSegError = new AllVideoSegmentsResponse(400, "error message");
		
		AppendVideoSegmentRequest appendVideoSegRequestNormal = new AppendVideoSegmentRequest("vsurl", "pid");
		AppendVideoSegmentRequest appendVideoSegRequestError = new AppendVideoSegmentRequest();
		
		AppendVideoSegmentResponse appendVideoSegResponseNormal = new AppendVideoSegmentResponse("response");
		AppendVideoSegmentResponse appendVideoSegReponseEmpty = new AppendVideoSegmentResponse("error message", 400);
		
		CreatePlaylistResponse createPlaylistResponseNormal = new CreatePlaylistResponse("response");
		CreatePlaylistResponse createPlaylistResponseEmpty = new CreatePlaylistResponse("error message", 400);
		
		CreateVideoSegmentRequest createVideoSegRequestNormal = new CreateVideoSegmentRequest("filename", "actor", "phrase", "encoded");
		CreateVideoSegmentRequest createVideoSegRequestEmpty = new CreateVideoSegmentRequest();
		
		CreateVideoSegmentResponse createVideoSegmentResponseNormal = new CreateVideoSegmentResponse(null, 200);
		CreateVideoSegmentResponse createVideoSegmentResponesError = new CreateVideoSegmentResponse(400, "error message");
		
		DeletePlaylistResponse deletePlaylistResponseNormal = new DeletePlaylistResponse("name", 200);
		DeletePlaylistResponse deletePlaylistResponesError = new DeletePlaylistResponse("response", 400, "error message");
		
		DeleteVideoSegmentRequest deleteVideoSegmentRequestNormal = new DeleteVideoSegmentRequest("url");
		DeleteVideoSegmentRequest deleteVideoSegmentRequestEmpty = new DeleteVideoSegmentRequest();
		
		DeleteVideoSegmentResponse deleteVideoSegmentResponseNormal = new DeleteVideoSegmentResponse("message", 200);
		DeleteVideoSegmentResponse deleteVideoSegmentResponseEmpty = new DeleteVideoSegmentResponse("response", 400, "error message");
	
		PlaylistRequest playlistRequestNormal = new PlaylistRequest();
		PlaylistRequest playlistRequestWithName = new PlaylistRequest("name");
		
		RemoteSegmentResponseObject remoteSegmentResponse = new RemoteSegmentResponseObject("url", "character", "text");
		
		RemoveSiteRequest removeSiteRequestNormal = new RemoveSiteRequest("url");
		RemoveSiteRequest removeSiteRequestEmpty = new RemoveSiteRequest();
		
		RemoveSiteResponse removeSiteResponseNormal = new RemoveSiteResponse("response", 200);
		RemoveSiteResponse removeSiteResponseEmpty = new RemoveSiteResponse("response", 400, "error message");
		
		RemoveVideoSegmentPlaylistRequest removeVideoSegmentRequestNormal = new RemoveVideoSegmentPlaylistRequest("PlaylistID", "VideoSegmentURL");
		
		RemoveVideoSegmentPlaylistResponse removeVideoSegmentPlaylistResponseNormal = new RemoveVideoSegmentPlaylistResponse(200, "message");
		
		SiteCreateRequest siteCreateRequestNormal = new SiteCreateRequest("url");
		SiteCreateRequest siteCreateRequestEmpty = new SiteCreateRequest();
		
		SiteCreateResponse siteCreateResponseNormal = new SiteCreateResponse("url", 200);
		SiteCreateResponse siteCreateResponseEmpty = new SiteCreateResponse(400, "error message");
		
		VideoSegmentMarkRequest videoSegMarkRequestNormal = new VideoSegmentMarkRequest("url", false);
		VideoSegmentMarkRequest videoSegMarkRequestEmpty = new VideoSegmentMarkRequest();
		
		VideoSegmentMarkResponse videoSegMarkResponseNormal = new VideoSegmentMarkResponse(400, "messages");
	}
	
}
