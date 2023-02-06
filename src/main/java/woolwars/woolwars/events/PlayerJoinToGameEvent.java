package woolwars.woolwars.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import woolwars.woolwars.game.Game;

public class PlayerJoinToGameEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final Game game;
    private final Player player;

    public PlayerJoinToGameEvent(Game game, Player player){
        this.game = game;
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }
}
