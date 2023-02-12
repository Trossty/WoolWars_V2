package woolwars.woolwars.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import woolwars.woolwars.utils.ItemBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class Game {

    private String uuid;

    private Set<UUID> playerList = new HashSet<>();
    private Set<UUID> bluePlayerList = new HashSet<>();
    private Set<UUID> redPlayerList = new HashSet<>();

    private Location location;

    @Setter
    private GameState state;

    private Map map;

    private int slot = 1;

    @Setter
    private int blueScore = 0;
    @Setter
    private int redScore = 0;

    @Setter
    private int time = 0;

    public Game(Map map, Location centerLocation, String name){
        this.map = map;
        this.location = centerLocation;
        uuid = name;


    }

}
