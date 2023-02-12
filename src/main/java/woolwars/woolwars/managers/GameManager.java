package woolwars.woolwars.managers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.events.PlayerJoinToGameEvent;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.game.GameClass;
import woolwars.woolwars.game.Map;
import woolwars.woolwars.game.states.PreLobbyState;
import woolwars.woolwars.utils.Colorize;
import woolwars.woolwars.utils.ItemBuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class GameManager {

    private WoolWarsPlugin plugin;

    private List<Game> gamesList = new ArrayList<>();

    public List<Map> mapList = new ArrayList<>();

    private List<GameClass> classes = new ArrayList<>();

    private GameClass archer;
    private GameClass assault;
    private GameClass engineer;
    private GameClass golem;
    private GameClass swordsman;
    private GameClass tank;

    public GameManager(WoolWarsPlugin plugin){

        this.plugin = plugin;


        archer = new GameClass("&7Archer",new ItemBuilder(Material.BOW).withDisplayName("&7Archer").getItemStack(),"woolwars.Archer");
        assault = new GameClass("&7Assault",new ItemBuilder(Material.SHEARS).withDisplayName("&7Assault").getItemStack(),"woolwars.Assault");
        engineer = new GameClass("&7Engineer",new ItemBuilder(Material.REDSTONE_BLOCK).withDisplayName("&7Engineer").getItemStack(),"woolwars.Engineer");
        golem = new GameClass("&7Golem",new ItemBuilder(Material.GOLDEN_CHESTPLATE).withDisplayName("&7Golem").getItemStack(),"woolwars.Golem");
        swordsman = new GameClass("&7Swordsman",new ItemBuilder(Material.STONE_SWORD).withDisplayName("&7Swordsman").getItemStack(),"woolwars.Swordsman");
        tank = new GameClass("&7Tank",new ItemBuilder(Material.IRON_BLOCK).withDisplayName("&7Tank").getItemStack(),"&7Tank");

        classes.addAll(Arrays.asList(archer,assault,engineer,golem,swordsman,tank));
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

            int randomInt = random.nextInt(mapList.size());

            if(mapList.get(randomInt).isLocationsReady()){
                addPlayerToGame(addGame(mapList.get(randomInt)),player);
            }else {
                Colorize.sendMessage(player,"&cSomething went wrong! Please try again.");
            }

        }else {

            for (int i = 0; i < gamesList.size(); i++) {
                if(gamesList.get(i).getState().getName().equalsIgnoreCase("Lobby")){
                    if(gamesList.get(i).getMap().isLocationsReady()){
                        addPlayerToGame(gamesList.get(i),player);
                    }
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
