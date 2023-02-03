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

    public List<Map> mapList = new ArrayList<>();

    public GameManager(WoolWarsPlugin plugin){

        this.plugin = plugin;

    }

    public void addGame(Map map, Location centerLocation,String name){
        Game game = new Game(map,centerLocation,name);
        game.setState(new PreLobbyState(plugin,game));
        gamesList.add(game);
    }

}
