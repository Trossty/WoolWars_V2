package woolwars.woolwars;

import lombok.Getter;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import woolwars.woolwars.api.GUI.GUIAPI;
import woolwars.woolwars.classes.EmptyChunkGenerator;
import woolwars.woolwars.command.MainCommand;
import woolwars.woolwars.game.Map;
import woolwars.woolwars.managers.GameManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public final class WoolWarsPlugin extends JavaPlugin {

    private GameManager gameManager;

    private World woolWarsMap;

    private GUIAPI<WoolWarsPlugin> guiapi;

    @Override
    public void onEnable() {
        gameManager = new GameManager(this);

        guiapi = new GUIAPI<>(this);


        getCommand("woolwars").setExecutor(new MainCommand(this));

        createFolders();

        loadWorld();
        loadMaps();

    }

    private void createFolders() {

        File schems = new File(getDataFolder(), "schematics");
        if (!schems.exists()) {
            schems.mkdirs();
        }

        File maps = new File(getDataFolder(), "maps");
        if (!maps.exists()) {
            maps.mkdirs();
        }

    }

    private void loadWorld() {

        woolWarsMap = Bukkit.createWorld(new WorldCreator("WoolWarsWorld").generator(new EmptyChunkGenerator()));

        woolWarsMap.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN,true);
        woolWarsMap.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        woolWarsMap.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS,false);
        woolWarsMap.setGameRule(GameRule.DISABLE_RAIDS,true);
        woolWarsMap.setGameRule(GameRule.DO_WEATHER_CYCLE,false);
        woolWarsMap.setGameRule(GameRule.DO_DAYLIGHT_CYCLE,false);
        woolWarsMap.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK,true);
        woolWarsMap.setGameRule(GameRule.DO_TRADER_SPAWNING,false);
        woolWarsMap.setGameRule(GameRule.MOB_GRIEFING,false);

    }

    private void loadMaps() {

        File folder = new File(getDataFolder()+"/maps/");

        for(File file : folder.listFiles()) {
            if (file.getName().endsWith(".yml")) {
                YamlConfiguration mapFile = YamlConfiguration.loadConfiguration(file);

                String mapName = mapFile.getString("Map.Name");
                String mapSchemName = mapFile.getString("Map.SchematicName");

                if(mapFile.contains("Map.Locations")){

                    Location redLocation = mapFile.getLocation("Map.Locations.RedLocation");
                    Location blueLocation = mapFile.getLocation("Map.Locations.BlueLocation");
                    Location centerLocation = mapFile.getLocation("Map.Locations.CenterLocation");

                    List<Location> upgradesLocations = new ArrayList<>();

                    for (String topic: mapFile.getConfigurationSection("Map.Locations.UpgradesLocations").getKeys(false)){
                        Location configLoc = mapFile.getLocation("Map.Locations.UpgradesLocations."+topic);
                        upgradesLocations.add(configLoc);
                    }

                    Map map = new Map(mapName, mapSchemName, redLocation, blueLocation, centerLocation, upgradesLocations);
                    gameManager.getMapList().add(map);
                }else {
                    Map map = new Map(mapName, mapSchemName, this);
                    gameManager.getMapList().add(map);
                }

            }
        }

    }

    @Override
    public void onDisable() {
    }

    public World getWoolWarsMap() {
        return woolWarsMap;
    }

    public GUIAPI<WoolWarsPlugin> getGUIAPI() {
        return guiapi;
    }
}
