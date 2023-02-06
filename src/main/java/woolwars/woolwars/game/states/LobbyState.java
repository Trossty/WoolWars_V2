package woolwars.woolwars.game.states;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;
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

        getGame().setTime(60*5);

        (new BukkitRunnable() {
            @Override
            public void run() {
                getGame().setTime(getGame().getTime()-1);
            }
        }).runTaskTimer(getPlugin(),0,20);

    }

    @Override
    public void onDisable(){

    }

    public void addPlayer(Player player){
        player.getInventory().clear();
        player.teleport(getGame().getLocation().clone().add(0,202,0));

    }

    @EventHandler
    public void playerJoinGame(PlayerJoinToGameEvent event){
        if(getGame() != event.getGame()) return;
        addPlayer(event.getPlayer());
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event){
        if(getPlugin().getGameManager().findGame(event.getPlayer())!=getGame()) return;
        event.setCancelled(event.getPlayer().getGameMode()!= GameMode.CREATIVE);
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event){
        if(getPlugin().getGameManager().findGame(event.getPlayer())!=getGame()) return;
        event.setCancelled(event.getPlayer().getGameMode()!= GameMode.CREATIVE);
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        if(getPlugin().getGameManager().findGame((Player) event.getEntity())!=getGame()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        if(getPlugin().getGameManager().findGame((Player) event.getEntity())!=getGame()) return;
        event.setCancelled(true);
    }

}
