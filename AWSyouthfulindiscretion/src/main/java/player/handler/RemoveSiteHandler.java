package player.handler;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import player.db.SiteDAO;
import player.http.RemoveSiteRequest;
import player.http.RemoveSiteResponse;

public class RemoveSiteHandler implements RequestHandler<RemoveSiteRequest, RemoveSiteResponse> {

	public LambdaLogger logger = null;

	@Override
	public RemoveSiteResponse handleRequest(RemoveSiteRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		RemoveSiteResponse response = null;
		logger.log(req.toString());

		SiteDAO dao = new SiteDAO();

		try {
			if (dao.removeSite(req.url)) {
				response = new RemoveSiteResponse(req.url, 200);
			} else {
				response = new RemoveSiteResponse(req.url, 409, "Could not find Site: " + req.url);
			}
		} catch (Exception e) {
			response = new RemoveSiteResponse(req.url, 400, "Unable to remove site: \"" + req.url + "\"(" + e.getMessage() + ")");
		}

		return response;
	}
	
}
