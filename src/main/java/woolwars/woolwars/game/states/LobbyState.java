package woolwars.woolwars.game.states;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.events.PlayerJoinToGameEvent;
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
            addPlayer(player);
        });

    }

    @Override
    public void onDisable(){

    }

    public void addPlayer(Player player){
        player.teleport(getGame().getLocation().clone().add(0,202,0));
    }

    @EventHandler
    public void playerJoinGame(PlayerJoinToGameEvent event){
        if(getGame() != event.getGame()) return;
        addPlayer(event.getPlayer());
    }

}
