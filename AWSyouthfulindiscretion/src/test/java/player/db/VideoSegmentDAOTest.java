package player.db;

import static org.junit.Assert.*;
import org.junit.Assert;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

import player.model.VideoSegment;

public class VideoSegmentDAOTest {

	@Test
	public void testGetVideoSegments() {
		VideoSegmentDAO dao = new VideoSegmentDAO();
		try {
			java.util.List<VideoSegment> list = dao.getAllVideoSegments();
			System.out.println(list.toString());
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

}
