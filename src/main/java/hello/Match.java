package hello;

import java.util.Date;

public class Match {

    private final long id;
    private final String localTeam;
    private final String awayTeam;
    private final String stadium;
    private final Date date;
	private final double longitude;
	private final double latitude;
	 

    public Match(long id, String localTeam, String awayTeam, String stadium, 
    		Date date, double longitude, double latitude) {
        this.id = id;
        this.localTeam = localTeam;
        this.awayTeam = awayTeam;
        this.stadium = stadium;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public long getId() {
        return id;
    }

    public String getLocalTeam() {
        return localTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getStadium() {
        return stadium;
    }

    public Date getDate() {
        return date;
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder retVal = new StringBuilder();
    	retVal.append(date.toString());
    	retVal.append("\t" + localTeam);
    	retVal.append("\t" + awayTeam);
    	retVal.append("\t" + stadium);
    	return retVal.toString();
    }

}
