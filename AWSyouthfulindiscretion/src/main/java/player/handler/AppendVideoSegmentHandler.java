package player.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import player.http.AppendVideoSegmentRequest;
import player.http.AppendVideoSegmentResponse;
import player.db.PlaylistDAO;

public class AppendVideoSegmentHandler implements RequestHandler<AppendVideoSegmentRequest, AppendVideoSegmentResponse>{

	LambdaLogger logger;
	
	/** Store into RDS.
	 * 
	 * @throws Exception
	 */
	 boolean appendVideoSegment(String vsurl, String plid) throws Exception {
		if (logger != null) {logger.log("in appendVideoSegment");}
		
		//create DAOs
		PlaylistDAO pdao = new PlaylistDAO();
		//Append to playlist DAO
		return pdao.appendToPlaylist(vsurl, plid);
		
	}
	
	@Override
	public AppendVideoSegmentResponse handleRequest(AppendVideoSegmentRequest req, Context context) {
		
		AppendVideoSegmentResponse response;
        try {
        	if (appendVideoSegment(req.vsurl, req.plid)) {
        		response = new AppendVideoSegmentResponse(req.plid);
        	}
        	else {
        		response = new AppendVideoSegmentResponse("Unable append video segment " + req.vsurl + " to playlist " + req.plid, 409);
        	}
        } catch (Exception e) {
        	response = new AppendVideoSegmentResponse(e.getMessage(), 400);
        }
        return response;
		
	}
	
	
}
