package data;

import java.util.ArrayList;

public class CricketTeam {
    private String name = "";
    private ArrayList<Cricketer> squad = new ArrayList<Cricketer>();
    private Cricketer[] playingXI = new Cricketer[11];

    public CricketTeam(String name) {
        this.name = name;
    }

    public boolean isInSquad(Cricketer player) {
        for (Cricketer cricketer : squad) {
            if (cricketer.equals(player)) {
                return true;
            }
        }
        return false;
    }

    // add player to squad
    public boolean addToSquad(Cricketer player) {
        if (!isInSquad(player)) {
            squad.add(player);
            return true;
        }
        return false;
    }

    // add player to playing xi
    public boolean addToXI(Cricketer player) {
        for (Cricketer cricketer : squad) {
            if (player.equals(cricketer)) {
                for (Cricketer inXI : playingXI) {
                    if (inXI != null && player.equals(inXI))
                        return false;
                }
                for (int i = 0; i < playingXI.length; i++) {
                    if (playingXI[i] == null) {
                        playingXI[i] = player;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // getters
    public Cricketer[] getPlayingXI() {
        return playingXI;
    }

    public ArrayList<Cricketer> getSquad() {
        return squad;
    }

    public String getName() {
        return name;
    }
}
