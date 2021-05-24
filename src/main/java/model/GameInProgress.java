package model;

import controller.GameWithFriend;

public class GameInProgress {
    private static GameWithFriend game;

    public static void setGame(GameWithFriend game) {
        GameInProgress.game = game;
    }

    public static GameWithFriend getGame() {
        return game;
    }
}
