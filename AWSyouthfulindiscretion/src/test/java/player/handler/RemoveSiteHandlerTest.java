package player.handler;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import player.handler.AddSiteHandler;
import player.http.SiteCreateRequest;
import player.http.SiteCreateResponse;
import player.handler.RemoveSiteHandler;
import player.http.RemoveSiteRequest;
import player.http.RemoveSiteResponse;


public class RemoveSiteHandlerTest extends LambdaTest {

	@Test
	public void testCreateAndDeleteSite() {
		try {
			SiteCreateRequest scr = new SiteCreateRequest("UnitTest");
			SiteCreateResponse s_resp = new AddSiteHandler().handleRequest(scr, createContext("add"));
			assertEquals(scr.url, s_resp.url);
			
			RemoveSiteRequest rsr = new RemoveSiteRequest("UnitTest");
			RemoveSiteResponse r_resp = new RemoveSiteHandler().handleRequest(rsr, createContext("remove"));
			assertEquals(rsr.url, r_resp.response);
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

}
