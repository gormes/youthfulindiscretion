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
		
	public VideoSegment getVideoSegment(String id) throws Exception {	// not sure if I want it to take in String or UUID
		try {															// will have to see based on implementation
			VideoSegment vs = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM videoSeg WHERE VideoSegID = ?;");
			ps.setString(1, id);
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
	
	
	public VideoSegment getVideoSegmentFromName(String fileName) throws Exception {	// not sure if I want it to take in String or UUID
		try {															// will have to see based on implementation
			VideoSegment vs = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM videoSeg WHERE s3BucketURL = ?;");
			ps.setString(1, "https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/" + fileName);
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
	
	public boolean addVideoSegment( VideoSegment vs) throws Exception {
		boolean result = false;
		PreparedStatement ps;
		try {
			
			ps = conn.prepareStatement("INSERT INTO videoSeg (s3BucketURL, videoSegID, actor, phrase) values(?,?,?,?);");
			ps.setString(1, vs.url);
			ps.setString(3, vs.actor);
			ps.setString(4, vs.phrase);
			ps.setString(2, vs.id.toString());
			ps.execute();
			ps.close();
			result = true;
			
		} catch (Exception e) {
			throw new Exception("Failed in adding Video Segment: " + e.getMessage());
		}
		return result;
	}
	
	public VideoSegment generateVideoSegment(ResultSet resultSet) throws Exception {
        UUID id  = UUID.fromString(resultSet.getString("VideoSegID"));
        String actor = resultSet.getString("actor");
        String phrase = resultSet.getString("phrase");
        String url = resultSet.getString("s3BucketURL");
        boolean mark = (resultSet.getInt("marked")==1) ? true : false;
        
        return new VideoSegment(actor, phrase, url, id, mark);
		
	}
	
	public boolean deleteVideoSegment(VideoSegment vs) throws Exception {
		try {
			if (getVideoSegment(vs.id.toString())==null) {
				return false;
			}
			else {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM videoSeg WHERE VideoSegID = ?;");
				ps.setString(1, vs.id.toString());
				ps.execute();
				ps.close();
				
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
            throw new Exception("Failed to insert constant: " + e.getMessage());
		}
	}
}
