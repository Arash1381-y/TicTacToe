import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Player;
import view.controllers.WelcomeController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        WelcomeController controller = new WelcomeController();
       readPlayersInfo();
        controller.run(args);
    }


    private static void readPlayersInfo() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("json.Json")));
            ArrayList<Player> players;
            players = new Gson().fromJson(json,
                    new TypeToken<List<Player>>() {
                    }.getType()
            );
            for (Player player : players) {
                Player.getAllPlayer().add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
