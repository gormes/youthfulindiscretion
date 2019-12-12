package player.model;

import java.util.UUID;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import player.model.VideoSegment;

@RunWith(JUnit4.class)
public class VideoSegmentTest {
	
	@Test
	public void test() {
		try {
			VideoSegment vs = new VideoSegment("Kirk", "Are you", "url");
			UUID vsID = vs.id;
			VideoSegment vs2 = new VideoSegment("Kirk", "Are you", "url", vsID, false);
			Assert.assertEquals(vs.equals(vs2), true);
			Assert.assertEquals(vs.getMarked(), false);
			
			vs2.setMarked(true);
			Assert.assertEquals(vs2.getMarked(), true);
			Assert.assertEquals(vs.equals(vs2), true);
			
			String notAVS = new String();
			Assert.assertEquals(vs2.equals(notAVS), false);
			
			VideoSegment vs3 = new VideoSegment("Kirk", "Are you", "url", vs.id);
			Assert.assertEquals(vs3.id, vs2.id);
		}
		catch(Exception e) {
			fail("test failed: " + e.getMessage());
		}
	}

}
