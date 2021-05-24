package model;

import java.util.ArrayList;

public class Player {
    static ArrayList<Player> allPlayer;

    static {
        allPlayer = new ArrayList<>();
    }

    String name;
    String password;
    int score;
    int wins;
    int looses;
    int draws;

    {
        score = 0;
    }

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        allPlayer.add(this);
    }

    public static ArrayList<Player> getAllPlayer() {
        return allPlayer;
    }

    public static Player getPlayerByName(String name) {
        for (Player player : allPlayer) {
            if (player.name.equals(name)) {
                return player;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean isPasswordCorrect(String password) {
        return password.equals(this.password);
    }

    public void setWins() {
        wins += 1;
        updateScore();
    }

    public void setLooses() {
        looses += 1;
    }

    public void setDraws() {
        draws += 1;
        updateScore();
    }

    public void updateScore() {
        score = wins * 3 + draws;
    }

    public int getWins() {
        return wins;
    }

    public int getScore() {
        return score;
    }

    public int getLooses() {
        return looses;
    }

    public int getDraws() {
        return draws;
    }

}
