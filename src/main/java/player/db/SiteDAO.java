package player.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import player.db.DatabaseUtil;
import player.model.Site;

/**
 * 
 * @author gwyneth ormes
 *
 */
public class SiteDAO {
	java.sql.Connection conn;
	
	 public SiteDAO() {
	    	try  {
	    		conn = DatabaseUtil.connect();
	    	} catch (Exception e) {
	    		conn = null;
	    	}
	    }

	 public List<Site> getAllConstants() throws Exception {
		List<Site> allSites new ArrayList<Site>();
		
	 }
}