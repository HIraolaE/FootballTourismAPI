package hello;

import java.text.SimpleDateFormat;
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
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = formatter.format(new Date());
		Match[] matches = matchController.match(stringDate,
				stringDate, new String("London"));
		for(Match match: matches)
			System.out.println(match);
		
	}

}
