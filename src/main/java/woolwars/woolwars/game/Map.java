package woolwars.woolwars.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import woolwars.woolwars.WoolWarsPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Map {

    private String schematicName;
    private String mapName;

    private YamlConfiguration config;

    private Location centerLocation;
    private Location redLocation;
    private Location blueLocation;

    private Location redShopLocation;

    private Location blueShopLocation;

    private List<Location> upgradesLocationList = new ArrayList<>();

    private boolean isLocationsReady;

    public Map(String schematicName, String mapName, YamlConfiguration config, WoolWarsPlugin plugin){

        this.schematicName = schematicName;
        this.mapName = mapName;

        this.config = config;

        centerLocation = plugin.getWoolWarsMap().getSpawnLocation();
        redLocation = plugin.getWoolWarsMap().getSpawnLocation();
        blueLocation = plugin.getWoolWarsMap().getSpawnLocation();
        upgradesLocationList.add(plugin.getWoolWarsMap().getSpawnLocation());

        isLocationsReady = false;

    }

    public Map(String schematicName, String mapName, YamlConfiguration config, Location centerLocation, Location redLocation, Location blueLocation, Location redShopLocation, Location blueShopLocation, List<Location> upgradesLocationList){
        this.schematicName = schematicName;
        this.mapName = mapName;
        this.config = config;
        this.centerLocation = centerLocation;
        this.redLocation = redLocation;
        this.blueLocation = blueLocation;
        this.upgradesLocationList = upgradesLocationList;
        this.redShopLocation = redShopLocation;
        this.blueShopLocation = blueShopLocation;
        isLocationsReady = true;
    }

}
