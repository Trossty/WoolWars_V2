package woolwars.woolwars.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.events.PlayerJoinToGameEvent;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.game.Map;
import woolwars.woolwars.game.states.PreLobbyState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class GameManager {

    private WoolWarsPlugin plugin;

    private List<Game> gamesList = new ArrayList<>();

    public List<Map> mapList = new ArrayList<>();

    public GameManager(WoolWarsPlugin plugin){

        this.plugin = plugin;

    }

    public Game addGame(Map map){
        String uuid = System.currentTimeMillis()+"";

        Game game = new Game(map,getNextLocation(),uuid);
        game.setState(new PreLobbyState(plugin,game));
        gamesList.add(game);
        return game;
    }

    public void placePlayerRandomGame(Player player){

        if(gamesList.isEmpty()){

            Random random = new Random();

            addPlayerToGame(addGame(mapList.get(random.nextInt(mapList.size()))),player);

        }else {

            for (int i = 0; i < gamesList.size(); i++) {
                if(gamesList.get(i).getState().getName().equalsIgnoreCase("Lobby")){
                    addPlayerToGame(gamesList.get(i),player);
                }
            }

        }

    }

    public void placePlayerSelectedMap(Player player, Map map){

        Optional<Game> gameOptional = gamesList.stream().filter(game -> game.getMap() == map).filter(game -> game.getState().getName().equalsIgnoreCase("Lobby")).findAny();

        if(gameOptional.isPresent()){
            addPlayerToGame(gameOptional.get(),player);
        }else {
            addPlayerToGame(addGame(map),player);
        }

    }

    public void addPlayerToGame(Game game, Player player){
        game.getPlayerList().add(player.getUniqueId());

        Bukkit.getPluginManager().callEvent(new PlayerJoinToGameEvent(game,player));

    }

    public Game findGame(Player player){
        AtomicReference<Game> respond = null;
        gamesList.forEach(game -> {
            if(game.getPlayerList().contains(player.getUniqueId())){
                respond.set(game);
            }
        });
        return respond.get();
    }


    //----------------------------------------------------//
    private boolean isSlotOptimal(int slot){
        return gamesList.stream().noneMatch(game -> game.getSlot() == slot);
    }

    private int optimalSlot(){
        int i = 0;
        while(!isSlotOptimal(i)){
            i++;
        }
        return i;
    }

    public Location getNextLocation(){

        World world = plugin.getWoolWarsMap();

        if(gamesList.isEmpty()){
            return new Location(world,0,100,0);
        }else {
            int a = optimalSlot() * 500;
            return new Location(world,a,100,a);
        }

    }
    //----------------------------------------------------//

    public List<Map> getMapList() {
        return mapList;
    }
}
