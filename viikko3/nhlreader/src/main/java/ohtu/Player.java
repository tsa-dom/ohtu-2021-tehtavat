
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private String assists;
    private String goals;
    private String penalties;
    private String team;
    private String games;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setAssists(String assists) {
        this.assists = assists;
    }
    
    public String getAssists() {
        return assists;
    }
    
    public void setGoals() {
        this.goals = goals;
    }
    
    public String getGoals() {
        return goals;
    }
    
    public void setPenalties(String penalties) {
        this.penalties = penalties;
    }
    
    public String getPenalties() {
        return penalties;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public String getTeam() {
        return team;
    }
    
    public void setGames(String games) {
        this.games = games;
    }
    
    public String getGames() {
        return games;
    }

    @Override
    public String toString() {
        while(name.length() < 22) {
            name += " ";
        }
        return name + " " + nationality + "\t" + goals + " + " + assists + " = " + (Integer.valueOf(assists) + Integer.valueOf(goals));
    }

    @Override
    public int compareTo(Player o) {
        return Integer.valueOf(o.goals) + Integer.valueOf(o.assists) - Integer.valueOf(this.goals) - Integer.valueOf(this.assists);
    }
      
}
