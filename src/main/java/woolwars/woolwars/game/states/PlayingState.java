package woolwars.woolwars.game.states;

import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.game.GamePlayer;
import woolwars.woolwars.game.GameState;

public class PlayingState extends GameState {

    public PlayingState(WoolWarsPlugin plugin, Game game) {
        super(plugin, "Playing", game);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event){
        if(getPlugin().getGameManager().findGame(event.getPlayer())!=getGame()) return;
        if(getGame().getState() != this) return;

        //TODO: Yün kırma koyma vb.

    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event){
        if(getPlugin().getGameManager().findGame(event.getPlayer())!=getGame()) return;
        if(getGame().getState() != this) return;
        event.setCancelled(event.getPlayer().getGameMode()!= GameMode.CREATIVE);
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        if(getPlugin().getGameManager().findGame((Player) event.getEntity())!=getGame()) return;
        if(getGame().getState() != this) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            Player victim = (Player) event.getEntity();
            GamePlayer victimGamePlayer = GamePlayer.getGamePlayer(victim).get();
            if(event.getDamager() instanceof  Player){
                Player damager = (Player) event.getDamager();
                GamePlayer damagerGamePlayer = GamePlayer.getGamePlayer(damager).get();
                if(damagerGamePlayer.getTeam() == victimGamePlayer.getTeam()){
                    event.setCancelled(true);
                }
            }else if(event.getDamager() instanceof Projectile){
                Projectile damagerItem = (Projectile) event.getDamager();
                if(damagerItem.getShooter() instanceof Player){
                    Player damager = (Player) damagerItem.getShooter();
                    GamePlayer damagerGamePlayer = GamePlayer.getGamePlayer(damager).get();
                    if(damagerGamePlayer.getTeam() == victimGamePlayer.getTeam()){
                        event.setCancelled(true);
                    }
                }
            }

        }
    }

}
