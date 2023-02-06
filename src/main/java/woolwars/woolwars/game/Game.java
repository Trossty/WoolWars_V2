package woolwars.woolwars.game;

import lombok.Getter;
import org.bukkit.Location;

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

    private GameState state;

    private Map map;

    private int slot = 1;

    private int time = 0;

    public Game(Map map, Location centerLocation, String name){
        this.map = map;
        this.location = centerLocation;
        uuid = name;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
