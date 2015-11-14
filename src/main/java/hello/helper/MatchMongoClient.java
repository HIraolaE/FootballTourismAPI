package hello.helper;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

import hello.Match;

public class MatchMongoClient {

	private MongoClient mongoClient;
	private DBCollection fixtures;
	
	public MatchMongoClient() {
		
		try {
			mongoClient = new MongoClient("localhost");
			DB gameMap = mongoClient.getDB("gamemap");
			fixtures = gameMap.getCollection("fixtures");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<Match> getMatchesNear(Double latitude, Double longitude, Date start, Date end) {
		
		BasicDBObject order = new BasicDBObject("start", 1);
		List<Match> retVal = new ArrayList<Match>();
		DBObject query = QueryBuilder.start("location").near(latitude, longitude, 5).and("start.timestamp").greaterThan(start.getTime()).get();
		DBCursor fixturesCursor = fixtures.find(query).sort(order);
		for(DBObject fixture: fixturesCursor) {
		
			Long id = Long.parseLong(fixture.get("id").toString());
			BasicDBObject teams = (BasicDBObject)fixture.get("teams");
			BasicDBObject homeTeam = (BasicDBObject)teams.get("home");
			BasicDBObject awayTeam = (BasicDBObject)teams.get("away");
			BasicDBObject venue = (BasicDBObject)fixture.get("venue");
			Calendar calendar = Calendar.getInstance();
			BasicDBObject startTime = (BasicDBObject)fixture.get("start");
			calendar.setTimeInMillis(Long.parseLong(startTime.getString("timestamp").toString()));
			Match match = new Match(id, getTeamName(homeTeam), getTeamName(awayTeam), venue.get("name").toString(), calendar.getTime());
			retVal.add(match);
		
		}
		return retVal;
		
	}
	
	private String getTeamName(BasicDBObject team) {

		String retVal = "";
		BasicDBObject teamName = (BasicDBObject)team.get("name");
		retVal = teamName.get("full").toString();
		return retVal;
		
	}
	
	public static void main(String[] args) {
		
		MatchMongoClient matchMongoClient = new MatchMongoClient();
		matchMongoClient.getMatchesNear(new Double(0.1281), new Double(51.5081), new Date(), new Date());

	}
		
}
