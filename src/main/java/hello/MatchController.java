package hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public Match[] match(@RequestParam(value="startDate", defaultValue="") String startDate,
			@RequestParam(value="endDate", defaultValue="") String endDate,
			@RequestParam(value="address", defaultValue="") String address){
		
		Date start = parseDate(startDate, true);
		Date end = parseDate(endDate, false);
		
		//List<Match> matches = matchClient.getMatchesNear(new Double(0.1281), new Double(51.5081), new Date(), new Date());
		List<Match> matches = matchClient.getMatchesNear(new Double(0.1281), 
				new Double(51.5081), start, end);
		Match[] retVal = new Match[matches.size()];
		retVal = matches.toArray(retVal);
		return retVal;
			
	}
	
	private Date parseDate(String date, boolean start){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(date != ""){	
			try{
				Date parsedDate = formatter.parse(date);
				return parsedDate;
			}catch(ParseException ex){
				Date  dateElement = new Date();
				if(start)
					dateElement.setYear(0);
				else
					dateElement.setYear(9999);
				return dateElement;
			}
		}else{
			Date  dateElement = new Date();
			if(start)
				dateElement.setYear(0);
			else
				dateElement.setYear(9999);
			return dateElement;
		}
	}
	
}
