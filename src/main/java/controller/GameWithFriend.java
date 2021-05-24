package controller;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import model.Player;

import java.util.ArrayList;

public class GameWithFriend {
    Player playerOne;
    Player playerTwo;
    Image XNeon;
    Image ONeon;
    private boolean isPlayerOneWinner;
    private int[][] mapHouseValue;
    private ArrayList<Rectangle> usedHouses;
    private boolean isO;

    {
        mapHouseValue = new int[3][3];
        isO = true;
        usedHouses = new ArrayList<>();
        XNeon = new Image("/pictures/neon X.jpg");
        ONeon = new Image("/pictures/neon O.jpg");
    }

    public GameWithFriend(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Image getSign() {
        if (isO) {
            isO = false;
            return ONeon;
        } else {
            isO = true;
            return XNeon;
        }
    }


    public boolean isDraw() {
        return usedHouses.size() == 9;
    }

    public void addRectangle(Rectangle rectangle) {
        usedHouses.add(rectangle);
    }

    public boolean isHouseUsedBefore(Rectangle rectangle) {
        return usedHouses.contains(rectangle);
    }

    public void setValueToHouse(int row, int column) {
        if (isO)
            mapHouseValue[row][column] = 4;
        else
            mapHouseValue[row][column] = 5;
        for (int[] ints : mapHouseValue) {
            for (int anInt : ints) {
                System.out.printf("%d ", anInt);
            }
            System.out.println();
        }
        System.out.println();
    }


    public boolean checkForWinner() {
        for (int[] row : mapHouseValue) {
            if (row[0] + row[1] + row[2] == 15) {
                isPlayerOneWinner = false;
                updateScore(playerTwo, playerOne);
                return true;
            }
            if (row[0] + row[1] + row[2] == 12) {
                isPlayerOneWinner = true;
                updateScore(playerOne, playerTwo);
                return true;
            }
        }
        for (int i = 0; i <= 2; i++) {
            if (mapHouseValue[0][i] + mapHouseValue[1][i] + mapHouseValue[2][i] == 12) {
                isPlayerOneWinner = true;
                updateScore(playerOne, playerTwo);
                return true;
            }
            if (mapHouseValue[0][i] + mapHouseValue[1][i] + mapHouseValue[2][i] == 15) {
                isPlayerOneWinner = false;
                updateScore(playerTwo, playerOne);
                return true;
            }
        }
        if (mapHouseValue[0][0] + mapHouseValue[1][1] + mapHouseValue[2][2] == 15) {
            isPlayerOneWinner = false;
            updateScore(playerTwo, playerOne);
            return true;
        }
        if (mapHouseValue[0][0] + mapHouseValue[1][1] + mapHouseValue[2][2] == 12) {
            isPlayerOneWinner = true;
            updateScore(playerOne, playerTwo);
            return true;
        }
        if (mapHouseValue[0][2] + mapHouseValue[1][1] + mapHouseValue[2][0] == 15) {
            isPlayerOneWinner = false;
            updateScore(playerTwo, playerOne);
            return true;
        }
        if (mapHouseValue[0][2] + mapHouseValue[1][1] + mapHouseValue[2][0] == 12) {
            isPlayerOneWinner = true;
            updateScore(playerOne, playerTwo);
            return true;
        }

        return false;
    }

    private void updateScore(Player winner, Player looser) {
        winner.setWins();
        looser.setLooses();
    }

    public boolean isPlayerOneWinner() {
        return isPlayerOneWinner;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Player getPlayerOne() {
        return playerOne;
    }
}

