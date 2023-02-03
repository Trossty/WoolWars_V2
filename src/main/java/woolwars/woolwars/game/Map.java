package woolwars.woolwars.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import woolwars.woolwars.WoolWarsPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Map {

    private String schematicName;
    private String mapName;

    private Location centerLocation;
    private Location redLocation;
    private Location blueLocation;
    private List<Location> upgradesLocationList = new ArrayList<>();

    private boolean isLocationsReady;

    public Map(String schematicName, String mapName, WoolWarsPlugin plugin){

        this.schematicName = schematicName;
        this.mapName = mapName;

        centerLocation = plugin.getWoolWarsMap().getSpawnLocation();
        redLocation = plugin.getWoolWarsMap().getSpawnLocation();
        blueLocation = plugin.getWoolWarsMap().getSpawnLocation();
        upgradesLocationList.add(plugin.getWoolWarsMap().getSpawnLocation());

        isLocationsReady = false;

    }

    public Map(String schematicName, String mapName, Location centerLocation, Location redLocation, Location blueLocation, List<Location> upgradesLocationList){
        this.schematicName = schematicName;
        this.mapName = mapName;
        this.centerLocation = centerLocation;
        this.redLocation = redLocation;
        this.blueLocation = blueLocation;
        this.upgradesLocationList = upgradesLocationList;
        isLocationsReady = true;
    }

}
