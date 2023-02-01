package woolwars.woolwars.game;

import lombok.Getter;
import woolwars.woolwars.game.states.PreLobbyState;

import javax.xml.stream.Location;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class Game {

    private Set<UUID> playerList = new HashSet<>();
    private Set<UUID> bluePlayerList = new HashSet<>();
    private Set<UUID> redPlayerList = new HashSet<>();

    private Location centerLocation;

    private GameState state;

    private Map map;

    private int time = 0;

    public Game(Map map, Location centerLocation){
        this.map = map;
        this.centerLocation = centerLocation;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
