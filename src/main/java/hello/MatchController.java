package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap; 
import java.util.*;
import java.text.*;

@RestController
public class MatchController {
	
	HashMap<String, String> stadiums;
	String[] teams = {"Arsenal","Aston Villa","Barnsley"};
	
	public MatchController() {
		stadiums = new HashMap<String,String>();
		stadiums.put("Arsenal", "Emirates Stadium, London");
		stadiums.put("Aston Villa", "Villa Park, Birmingham");
		stadiums.put("Barnsley", "Oakwell, Barnsley");
		
	}
	
	private final AtomicLong counter = new AtomicLong();

	
	@RequestMapping("/match")
	public Match[] match(@RequestParam(value="startDate", defaultValue="") String startDate){
	//	Match[] matchesToRet = new Match[matches.length];
		Calendar cal = Calendar.getInstance();
	    Date date = new Date();
		cal.setTime(date);
	    
		//System.out.println(startDate);
		
		int nTeams = teams.length;
		Match[] matchesToRet = new Match[nTeams*nTeams];
		int i=0;
		for(String homeTeam: teams){
			for(String awayTeam: teams){
				//cal.add(Calendar.DATE, i);
				cal.add(Calendar.DATE, 1);
				matchesToRet[i++] = new Match(counter.incrementAndGet(),
								homeTeam, awayTeam,
								stadiums.get(homeTeam), cal.getTime());   
			}
		}
		Match[] selectedMatches = new Match[nTeams*nTeams];
		
		if (startDate != ""){
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date argDate = formatter.parse(startDate);
				
				int j=0;
				for (Match m : matchesToRet){	
					if(m.getDate().compareTo(argDate) != -1){
						selectedMatches[j++] = m;
					}
				}
				

				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return selectedMatches;
		} else {
			return matchesToRet; 
		}

	}
}
