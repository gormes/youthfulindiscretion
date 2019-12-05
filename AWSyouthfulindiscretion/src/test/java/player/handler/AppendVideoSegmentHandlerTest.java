package player.handler;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import player.http.AppendVideoSegmentRequest;
import player.http.AppendVideoSegmentResponse;

public class AppendVideoSegmentHandlerTest extends LambdaTest {

	@Test
	public void test() {

		String vsid = "981da0fb-e54b-485e-ba33-bc185781b266";
		String plid = "7084dae8-7373-4f35-aa39-8477ad0c4b23";		
		AppendVideoSegmentHandler handler = new AppendVideoSegmentHandler();
		AppendVideoSegmentRequest request = new AppendVideoSegmentRequest(vsid, plid);
		AppendVideoSegmentResponse response;
		response = handler.handleRequest(request, createContext("append"));
		Assert.assertEquals(request.plid, response.response);
	}
	


}
