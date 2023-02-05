package woolwars.woolwars.game.states;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.game.GameState;

public class LobbyState extends GameState {

    public LobbyState(WoolWarsPlugin plugin, Game game) {
        super(plugin, "Lobby", game);
    }

    @Override
    public void onEnable(){

        getGame().getPlayerList().forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            player.teleport(getGame().getLocation().clone().add(0,203,0));
        });

    }

    @Override
    public void onDisable(){

    }

}
