package player.db;

import static org.junit.Assert.*;
import org.junit.Assert;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

import player.model.VideoSegment;

public class VideoSegmentDAOTest {

	@Test
	public void testGetVideoSegment() {
		VideoSegmentDAO dao = new VideoSegmentDAO();
		try {
			VideoSegment vs = new VideoSegment("testActor", "testPhrase", "testURL");
			dao.addVideoSegment(vs);
			VideoSegment vsBack = dao.getVideoSegment(vs.id.toString());
			assertTrue(vs.equals(vsBack));
			dao.deleteVideoSegment(vs.url);
		}
		catch (Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}
	
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
