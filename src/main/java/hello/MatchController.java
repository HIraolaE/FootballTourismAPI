package hello;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.helper.MatchMongoClient; 

@RestController
public class MatchController {
	
	private MatchMongoClient matchClient;
	
	public MatchController() {
		
		matchClient = new MatchMongoClient();
		
	}
	
	@RequestMapping("/match")
	public Match[] match(@RequestParam(value="startDate", defaultValue="") String startDate){
		
		List<Match> matches = matchClient.getMatchesNear(new Double(0.1281), new Double(51.5081), new Date(), new Date());
		Match[] retVal = new Match[matches.size()];
		retVal = matches.toArray(retVal);
		return retVal;
		
	}
	
}
