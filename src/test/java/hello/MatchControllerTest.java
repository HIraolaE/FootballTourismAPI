package hello;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class MatchControllerTest {

	private MatchController matchController;
	
	@Before
	public void setup() throws Exception {
		
		matchController = new MatchController();
		
	}
	
	@Test
	public void testMatch() {
		
		Match[] matches = matchController.match(new Date().toString());
		for(Match match: matches)
			System.out.println(match);
		
	}

}
