package player.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import player.model.VideoSegment;

public class VideoSegmentDAO {
	
	java.sql.Connection conn;
	
	public VideoSegmentDAO() {
		try {
			conn = DatabaseUtil.connect();
		}
		catch (Exception e) {
			conn = null;
		}
	}
		
	public VideoSegment getVideoSegment(UUID id) throws Exception {	// not sure if I want it to take in String or UUID
		try {														// will have to see based on implementation
			VideoSegment vs = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM videoSeg WHERE VideoSegID = ?;");
			ps.setString(1, id.toString());
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				vs = generateVideoSegment(resultSet);
			}
			resultSet.close();
			ps.close();
			
			return vs;
			
        } catch (Exception e) {
            throw new Exception("Failed in getting Video Segment: " + e.getMessage());
        }
	}
	
	public List<VideoSegment> getAllVideoSegments() throws Exception {
		List<VideoSegment> allVideoSegments = new ArrayList<VideoSegment>();
	try {	
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM videoSeg;";
		ResultSet resultSet = statement.executeQuery(query);
		
		while (resultSet.next()) {
            VideoSegment vs = generateVideoSegment(resultSet);
            allVideoSegments.add(vs);
        }
        resultSet.close();
        statement.close();
		return allVideoSegments;

    } catch (Exception e) {
        throw new Exception("Failed in getting Video Segments: " + e.getMessage());
    }
	}
	
	public VideoSegment generateVideoSegment(ResultSet resultSet) throws Exception {
        UUID id  = UUID.fromString(resultSet.getString("VideoSegID"));
        String actor = resultSet.getString("actor");
        String phrase = resultSet.getString("phrase");
        String url = resultSet.getString("s3BucketURL");
        
        return new VideoSegment(actor, phrase, url, id);
		
	}
}
