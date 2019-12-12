package player.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import player.model.Playlist;
import player.model.VideoSegment;
import player.db.VideoSegmentDAO;

public class PlaylistDAO {

	java.sql.Connection conn;

	public PlaylistDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public Playlist getPlaylist(String id) throws Exception { // not sure if I want it to take in String or UUID
		try { // will have to see based on implementation
			Playlist p = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE playlistName = ?;");
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
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

		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM playlist;";
			ResultSet resultSet = statement.executeQuery(query);

			HashMap<UUID, ArrayList<String>> list = new HashMap<UUID, ArrayList<String>>();
			while (resultSet.next()) {
				UUID currID = UUID.fromString(resultSet.getString("playlistName"));
				ArrayList<String> vslist = (list.get(currID) == null) ? new ArrayList<String>() : list.get(currID);
				String entry = resultSet.getString("s3BucketURL");
				if (null != entry && !"".equals(entry))
					vslist.add(entry);
				list.put(currID, vslist);
			}
			resultSet.close();
			statement.close();
			return listify(list);

		} catch (Exception e) {
			throw new Exception("Failed in getting Playlist: " + e.getMessage());
		}
	}

	private static List<Playlist> listify(HashMap<UUID, ArrayList<String>> map) throws Exception {
		List<Playlist> ret = new ArrayList<Playlist>();
		Playlist currPlaylist = null;
		VideoSegmentDAO dao = new VideoSegmentDAO();
		try {
			Iterator<Entry<UUID, ArrayList<String>>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				HashMap.Entry<UUID, ArrayList<String>> pair = (HashMap.Entry<UUID, ArrayList<String>>) it.next();
				currPlaylist = new Playlist(pair.getKey());
				for (String s : pair.getValue()) {
					currPlaylist.videoSegments.add(dao.getVideoSegmentFromURL(s));
				}
				ret.add(currPlaylist);
				it.remove(); // avoids a ConcurrentModificationException
			}
			return ret;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean addPlaylist(Playlist p) throws Exception {
		try {
			PreparedStatement ps;
			if (p.videoSegments.isEmpty()) {
				ps = conn.prepareStatement("INSERT INTO playlist (playlistName,s3BucketURL) values(?,?);");
				ps.setString(1, p.id.toString());
				ps.setString(2, "");
				ps.execute();
				ps.close();
				return true;
			} else
				return false;
		} catch (Exception e) {
			throw new Exception("Failed to add Playlist: " + e.getMessage());
		}
	}

	public boolean deletePlaylist(Playlist p) throws Exception {
		try {
			// check if it is in the database
			if (getPlaylist(p.id.toString()) == null)
				return false;
			PreparedStatement ps = conn.prepareStatement("DELETE FROM playlist WHERE playlistName=?;");
			ps.setString(1, p.id.toString());
			ps.execute();
			ps.close();

			return true;
		} catch (Exception e) {
			throw new Exception("Failed to delete Playlist: " + e.getMessage());
		}
	}

	private Playlist appendToPlaylist(ResultSet resultSet, Playlist p) throws Exception {
		VideoSegmentDAO dao = new VideoSegmentDAO();
		String currid = resultSet.getString("s3BucketURL");
		VideoSegment currvs = dao.getVideoSegmentFromURL(currid);
		if (currvs != null)
			p.videoSegments.add(currvs);
		return p;
	}

	public boolean appendToPlaylist(String url, String pid) throws Exception {
		try {
			Playlist p = getPlaylist(pid);
			if (p == null)
				return false;
			else if (p.videoSegments.isEmpty()) {
				PreparedStatement ps = conn.prepareStatement("UPDATE playlist SET s3BucketURL=? WHERE playlistName=?;");
				ps.setString(1, url);
				ps.setString(2, pid);
				ps.execute();
				ps.close();
				return true;
			} else {
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO playlist (playlistName, s3BucketURL) values(?,?);");
				ps.setString(1, pid);
				ps.setString(2, url);
				ps.execute();
				ps.close();
				return true;
			}
		} catch (Exception e) {
			throw new Exception("Failed to append video segment: " + url + " into playlist: " + pid + e.getMessage());
		}
	}

	public boolean findVideoSegment(String pID, String vsURL) {
		boolean response = false;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE playlistName = ? AND s3BucketURL = ?;");
			ps.setString(1, pID);
			ps.setString(2, vsURL);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				response = true;
			}
			resultSet.close();
			ps.close();
			
		} catch (Exception e) {
			response = false;
		}
		return response;
	}

	public void deleteFromPlaylist(String pID, String vsURL) throws Exception {
		try {
			if (getPlaylist(pID).videoSegments.size()==1) {
				PreparedStatement ps = conn.prepareStatement("UPDATE playlist SET s3BucketURL=? WHERE playlistName=?;");
				ps.setString(1, "");
				ps.setString(2, pID);
				ps.executeUpdate();
				ps.close();
			}
			else {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM playlist WHERE (playlistName = ? AND s3BucketURL = ?) limit 1;");
				ps.setString(1, pID);
				ps.setString(2, vsURL);
				int response = ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			throw new Exception("Unable to remove " + vsURL + " from " + pID);
		}
	}

}
