package player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import player.http.AllPlaylistsResponse;
import player.http.AllSitesResponse;
import player.http.AllUnmarkedVideoSegmentsResponse;
import player.http.AllVideoSegmentsResponse;
import player.http.AppendVideoSegmentRequest;
import player.http.AppendVideoSegmentResponse;
import player.http.CreatePlaylistResponse;
import player.http.CreateVideoSegmentRequest;
import player.http.CreateVideoSegmentResponse;
import player.http.DeletePlaylistResponse;
import player.http.DeleteVideoSegmentRequest;
import player.http.DeleteVideoSegmentResponse;
import player.http.PlaylistRequest;
import player.http.RemoteSegmentResponseObject;
import player.http.RemoveSiteRequest;
import player.http.RemoveSiteResponse;
import player.http.RemoveVideoSegmentPlaylistRequest;
import player.http.RemoveVideoSegmentPlaylistResponse;
import player.http.SiteCreateRequest;
import player.http.SiteCreateResponse;
import player.http.VideoSegmentMarkRequest;
import player.http.VideoSegmentMarkResponse;

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
		appendVideoSegRequestNormal.setPlaylistID("playlist test");
		appendVideoSegRequestNormal.setVideoSegmentID("video segment test");
		assertEquals(appendVideoSegRequestNormal.getPlaylistID().equals("playlist test"), true);
		assertEquals(appendVideoSegRequestNormal.getVideoSegmentID().equals("video segment test"), true);
		
		AppendVideoSegmentResponse appendVideoSegResponseNormal = new AppendVideoSegmentResponse("response");
		AppendVideoSegmentResponse appendVideoSegReponseEmpty = new AppendVideoSegmentResponse("error message", 400);
		
		CreatePlaylistResponse createPlaylistResponseNormal = new CreatePlaylistResponse("response");
		CreatePlaylistResponse createPlaylistResponseEmpty = new CreatePlaylistResponse("error message", 400);
		
		CreateVideoSegmentRequest createVideoSegRequestNormal = new CreateVideoSegmentRequest("filename", "actor", "phrase", "encoded");
		CreateVideoSegmentRequest createVideoSegRequestEmpty = new CreateVideoSegmentRequest();
		createVideoSegRequestNormal.setFileName("file test");
		createVideoSegRequestNormal.setActor("actor test");
		createVideoSegRequestNormal.setPhrase("phrase test");
		createVideoSegRequestNormal.setEncodedContents("contents test");
		assertEquals(createVideoSegRequestNormal.getFileName().equals("file test"), true);
		assertEquals(createVideoSegRequestNormal.actor().equals("actor test"), true);
		assertEquals(createVideoSegRequestNormal.phrase().equals("phrase test"), true);
		assertEquals(createVideoSegRequestNormal.encodedContents().equals("contents test"), true);
		
		CreateVideoSegmentResponse createVideoSegmentResponseNormal = new CreateVideoSegmentResponse(null, 200);
		CreateVideoSegmentResponse createVideoSegmentResponesError = new CreateVideoSegmentResponse(400, "error message");
		
		DeletePlaylistResponse deletePlaylistResponseNormal = new DeletePlaylistResponse("name", 200);
		DeletePlaylistResponse deletePlaylistResponesError = new DeletePlaylistResponse("response", 400, "error message");
		
		DeleteVideoSegmentRequest deleteVideoSegmentRequestNormal = new DeleteVideoSegmentRequest("url");
		DeleteVideoSegmentRequest deleteVideoSegmentRequestEmpty = new DeleteVideoSegmentRequest();
		deleteVideoSegmentRequestNormal.setvsId("vs test");
		assertEquals(deleteVideoSegmentRequestNormal.getvsID().equals("vs test"), true);
		
		DeleteVideoSegmentResponse deleteVideoSegmentResponseNormal = new DeleteVideoSegmentResponse("message", 200);
		DeleteVideoSegmentResponse deleteVideoSegmentResponseEmpty = new DeleteVideoSegmentResponse("response", 400, "error message");
	
		PlaylistRequest playlistRequestNormal = new PlaylistRequest();
		PlaylistRequest playlistRequestWithName = new PlaylistRequest("name");
		
		RemoteSegmentResponseObject remoteSegmentResponse = new RemoteSegmentResponseObject("url", "character", "text");
		assertEquals(remoteSegmentResponse.getUrl().equals("url"), true);
		assertEquals(remoteSegmentResponse.getCharacter().equals("character"), true);
		assertEquals(remoteSegmentResponse.getText().equals("text"), true);
		
		
		RemoveSiteRequest removeSiteRequestNormal = new RemoveSiteRequest("url");
		RemoveSiteRequest removeSiteRequestEmpty = new RemoveSiteRequest();
		removeSiteRequestNormal.setID("url test");
		assertEquals(removeSiteRequestNormal.getID().equals("url test"), true);
		
		RemoveSiteResponse removeSiteResponseNormal = new RemoveSiteResponse("response", 200);
		RemoveSiteResponse removeSiteResponseEmpty = new RemoveSiteResponse("response", 400, "error message");
		
		RemoveVideoSegmentPlaylistRequest removeVideoSegmentRequestNormal = new RemoveVideoSegmentPlaylistRequest("PlaylistID", "VideoSegmentURL");
		RemoveVideoSegmentPlaylistRequest removeVideoSegmentRequestError = new RemoveVideoSegmentPlaylistRequest();
		removeVideoSegmentRequestNormal.setPlaylistID("playlist test");
		removeVideoSegmentRequestNormal.setVideoSegmentURL("video segment test");
		assertEquals(removeVideoSegmentRequestNormal.getPlaylistID().equals("playlist test") , true);
		assertEquals(removeVideoSegmentRequestNormal.getVideoSegmentURL().equals("video segment test") , true);
		
		RemoveVideoSegmentPlaylistResponse removeVideoSegmentPlaylistResponseNormal = new RemoveVideoSegmentPlaylistResponse(200, "message");
		RemoveVideoSegmentPlaylistResponse removeVideoSegmentPlaylistResponseError = new RemoveVideoSegmentPlaylistResponse();
		
		SiteCreateRequest siteCreateRequestNormal = new SiteCreateRequest("url");
		SiteCreateRequest siteCreateRequestEmpty = new SiteCreateRequest();
		siteCreateRequestNormal.setURL("test url");
		assertEquals(siteCreateRequestNormal.getURL().equals("test url"), true);
		
		SiteCreateResponse siteCreateResponseNormal = new SiteCreateResponse("url", 200);
		SiteCreateResponse siteCreateResponseEmpty = new SiteCreateResponse(400, "error message");
		
		VideoSegmentMarkRequest videoSegMarkRequestNormal = new VideoSegmentMarkRequest("url", false);
		VideoSegmentMarkRequest videoSegMarkRequestEmpty = new VideoSegmentMarkRequest();
		videoSegMarkRequestNormal.setMarked(true);
		videoSegMarkRequestNormal.setUrl("url test");
		assertEquals(videoSegMarkRequestNormal.getMarked(), true);
		assertEquals(videoSegMarkRequestNormal.getUrl().equals("url test"), true);
		
		VideoSegmentMarkResponse videoSegMarkResponseNormal = new VideoSegmentMarkResponse(400, "messages");
	}
	
}
