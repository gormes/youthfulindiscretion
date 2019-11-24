package player;

import java.util.UUID;

import static org.junit.Assert.*;

import org.junit.Test;

import player.model.VideoSegment;

public class VideoSegmentTest {
	
	@Test
	public void test() {
		try {
			VideoSegment vs = new VideoSegment("testFile", "Kirk", "Are you", new UUID(16, 16));
			System.out.println(vs.id.toString());
		}
		catch(Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

}
