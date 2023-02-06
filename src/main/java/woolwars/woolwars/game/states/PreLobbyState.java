package woolwars.woolwars.game.states;

import org.bukkit.scheduler.BukkitRunnable;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.game.GameState;
import woolwars.woolwars.worldedit.WorldEditHandler;

import java.io.File;

public class PreLobbyState extends GameState {

    public PreLobbyState(WoolWarsPlugin plugin, Game game) {
        super(plugin, "PreLobby", game);
    }

    @Override
    public void onEnable(){

        WorldEditHandler.paste(new File(getPlugin().getDataFolder(), "schematics/"+getGame().getMap().getSchematicName()+".schem"), getGame().getLocation());

        WorldEditHandler.paste(new File(getPlugin().getDataFolder(), "schematics/lobby.schem"), getGame().getLocation().clone().add(0,200,0));

        (new BukkitRunnable() {
            @Override
            public void run() {
                onDisable();
            }
        }).runTaskLater(getPlugin(),20*5);

    }

    @Override
    public void onDisable(){

        getGame().setState(new LobbyState(getPlugin(),getGame()));

    }

}
