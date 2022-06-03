import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players = new ArrayList<Player>();
    public int runs = 0, wickets = 0, balls = 0;

    public Team(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addPlayer(Player player) {
        if (!inSquad(player)) {
            this.players.add(player);
            return true;
        }
        return false;
    }

    private boolean inSquad(Player p) {
        for (Player player : players) {
            if (p.equals(player)) return true;
        }
        return false;
    }

    public boolean equals(Team other) {
        return this.name.equalsIgnoreCase(other.name);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String listPlayers() {
        String playersList = "";
        for (int i = 0; i<players.size(); i++) {
            playersList = playersList.concat(Integer.toString(i + 1) + ". " + players.get(i).getName() + "\n");
        }
        return playersList;
    }

    public String getOvers() {
        return Integer.toString(balls/6) + "." + Integer.toString(balls%6);
    }
    
}
