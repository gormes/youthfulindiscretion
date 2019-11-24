package player.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import player.model.Playlist;
import player.model.VideoSegment;
import player.db.VideoSegmentDAO;

public class PlaylistDAO {

	
	java.sql.Connection conn;
	
	public PlaylistDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
	}
		
	public Playlist getPlaylist(String id) throws Exception {	// not sure if I want it to take in String or UUID
		try {													// will have to see based on implementation
			Playlist p = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE playlistName = ?;");
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				if (p == null) // creates a new playlist if there is at least one entry
					p = new Playlist(UUID.fromString(resultSet.getString("playlistName")));
				appendToPlaylist(resultSet, p);
			}
			resultSet.close();
			ps.close();
			return p;
			
        } catch (Exception e) {
            throw new Exception("Failed in getting Playlist: " + e.getMessage());
        }
	}
	
	public List<Playlist> getAllPlaylists() throws Exception {
		List<Playlist> allPlaylists = new ArrayList<Playlist>();
		Playlist lastPlaylist = null;
		Playlist currPlaylist = null;
	try {	
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM playlist;";
		ResultSet resultSet = statement.executeQuery(query);
		
		while (resultSet.next()) {
			UUID currID = UUID.fromString(resultSet.getString("playlistName"));
			if (lastPlaylist == null || !lastPlaylist.id.equals(currID)) 
				currPlaylist = new Playlist(currID);
			else
				currPlaylist = lastPlaylist;
			appendToPlaylist(resultSet, currPlaylist);
        }
        resultSet.close();
        statement.close();
		return allPlaylists;

    } catch (Exception e) {
        throw new Exception("Failed in getting Playlist: " + e.getMessage());
    }
	}
	
	public Playlist appendToPlaylist(ResultSet resultSet, Playlist p) throws Exception {
		VideoSegmentDAO dao = new VideoSegmentDAO();
		String currid = resultSet.getString("videoSegID");
		VideoSegment currvs = dao.getVideoSegment(currid);
		p.videoSegments.add(currvs);
        return p;
		
	}
}
