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

	 public List<Site> getAllSites() throws Exception {
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
	 
	public Site getSite(String url) throws Exception {
		try {
			Site site = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM sites WHERE siteName = ?;");
			ps.setString(1, url);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				site = new Site(resultSet.getString("siteName"));
			}
			resultSet.close();
			ps.close();
			return site;
		} catch (Exception e) {
			throw new Exception("Failed in getting site");
		}
	}
	 
	public boolean addSite(String url) throws Exception {
		boolean result = false;
		PreparedStatement ps;
		 
		try {
			ps = conn.prepareStatement("INSERT INTO sites (siteName) values(?);");
			ps.setString(1, url);
			ps.execute();
			ps.close();
			result = true;
		} catch(Exception e) {
			throw new Exception("Failed in getting site URL: " + e.getMessage());
		}
		return result;
	 }
	
	public boolean removeSite(String url) throws Exception {
		try {
			if (getSite(url)==null)
				return false;
			PreparedStatement ps = conn.prepareStatement("DELETE FROM sites WHERE siteName=?;");
			ps.setString(1, url);
			ps.execute();
			ps.close();
			return true;
		}
		catch (Exception e) {
			throw new Exception("");
		}
	}
}