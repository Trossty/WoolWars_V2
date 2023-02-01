package woolwars.woolwars.game;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import woolwars.woolwars.WoolWarsPlugin;

public class GameState implements Listener {

    private final WoolWarsPlugin plugin;

    private final Game game;

    private final String name;

    public GameState(final WoolWarsPlugin plugin, String name, Game game){
        this.plugin = plugin;
        this.name = name;
        this.game = game;
    }

    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this,getPlugin());
    }

    public void onDisable(){
        HandlerList.unregisterAll(this);
    }

    protected WoolWarsPlugin getPlugin(){
        return this.plugin;
    }

    public String getName() {
        return name;
    }

}
