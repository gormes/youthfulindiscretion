package player.model;

public class Site {
	public final String url;
	
	public Site(String url) {
		this.url = url;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (o instanceof Site) {
			Site other = (Site) o;
			return url.equals(other.url);
		}
		
		return false;  // not a Site
	}
}
