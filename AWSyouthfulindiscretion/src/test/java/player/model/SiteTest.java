package player.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SiteTest {

	@Test
	public void test() {
		Site site = new Site("TestURL");
		assertEquals(true, site.equals(site));
		assertEquals(false, site.equals(new Object()));
		assertEquals(false, site.equals(null));
		
	}
}
