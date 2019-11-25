package player.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import player.db.DatabaseUtil;
import player.model.Site;

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
		List<Site> allSites = new ArrayList<Site>();
		try {
			Statement statement = conn.createStatement();
			String query  = "SELECT * FROM innodb.sites;";
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				Site site = new Site(resultSet.getString("siteName"));
				allSites.add(site);
			}
			resultSet.close();
			statement.close();
			return allSites;
		} catch(Exception e) {
			throw new Exception("Failed in getting site URL: " + e.getMessage());
		}
	 }
}