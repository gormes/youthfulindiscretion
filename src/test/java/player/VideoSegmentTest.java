package player;

import java.util.UUID;

import static org.junit.Assert.*;

import org.junit.Test;

import player.model.VideoSegment;

public class VideoSegmentTest {
	
	@Test
	public void test() {
		VideoSegment vs = new VideoSegment("testFile", "Kirk", "Are you");
		System.out.println(vs.id.toString());
		assertEquals(1, 1);
	}

}
