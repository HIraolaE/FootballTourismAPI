package hello;

import java.util.Date;

public class Match {

    private final long id;
    private final String localTeam;
    private final String awayTeam;
    private final String stadium;
    private final Date date;
	 

    public Match(long id, String localTeam, String awayTeam, String stadium, Date date) {
        this.id = id;
        this.localTeam = localTeam;
        this.awayTeam = awayTeam;
        this.stadium = stadium;
        this.date = date;
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

}
