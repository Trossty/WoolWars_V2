package woolwars.woolwars;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import woolwars.woolwars.managers.GameManager;

@Getter
public final class WoolWarsPlugin extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {
        gameManager = new GameManager(this);
    }

    @Override
    public void onDisable() {
    }



}
