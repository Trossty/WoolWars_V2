package woolwars.woolwars.game.states;

import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.game.GameState;

public class PlayingState extends GameState {
    public PlayingState(WoolWarsPlugin plugin, Game game) {
        super(plugin, "Playing", game);
    }
}
