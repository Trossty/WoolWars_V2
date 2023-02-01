package woolwars.woolwars.managers;

import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.game.Map;
import woolwars.woolwars.game.states.PreLobbyState;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private WoolWarsPlugin plugin;

    private List<Game> gamesList = new ArrayList<>();

    public GameManager(WoolWarsPlugin plugin){

        this.plugin = plugin;

    }

    public void addGame(Map map, Location centerLocation){
        Game game = new Game(map,centerLocation);
        game.setState(new PreLobbyState(plugin,game));

    }

}
