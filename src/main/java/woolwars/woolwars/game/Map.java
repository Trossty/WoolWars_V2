package woolwars.woolwars.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.List;

@Getter
@Setter
public class Map {

    private String schematicName;
    private String mapName;

    private Location centerLocation;
    private Location redLocation;
    private Location blueLocation;
    private List<Location> upgradesLocationList;

    private boolean isLocationsReady;

}
