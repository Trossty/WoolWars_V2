package woolwars.woolwars.game.states;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.events.PlayerJoinToGameEvent;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.game.GamePlayer;
import woolwars.woolwars.game.GameState;
import woolwars.woolwars.objects.guis.TeamSelectorGUI;
import woolwars.woolwars.utils.ItemBuilder;

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

                if(getGame().getTime() == 0){
                    cancel();
                    onDisable();
                }

                if(getGame().getPlayerList().size()>5){
                    if(getGame().getTime()>10){
                        getGame().setTime(10);
                    }
                }

                getGame().setTime(getGame().getTime()-1);
            }
        }).runTaskTimer(getPlugin(),0,20);

    }

    @Override
    public void onDisable(){
        getGame().setState(new PlayingState(getPlugin(),getGame()));
    }

    public void addPlayer(Player player){
        player.getInventory().clear();
        player.teleport(getGame().getLocation().clone().add(0,202,0));

        GamePlayer gamePlayer = new GamePlayer(player.getUniqueId());

        player.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).withDisplayName("&bTeam Selector").getItemStack());

    }

    @EventHandler
    public void playerJoinGame(PlayerJoinToGameEvent event){
        if(getGame() != event.getGame()) return;
        if(getGame().getState() != this) return;
        // ^ this shit is gay

        addPlayer(event.getPlayer());
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event){
        if(getPlugin().getGameManager().findGame(event.getPlayer())!=getGame()) return;
        if(getGame().getState() != this) return;
        event.setCancelled(event.getPlayer().getGameMode()!= GameMode.CREATIVE);
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
    public void onDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        if(getPlugin().getGameManager().findGame((Player) event.getEntity())!=getGame()) return;
        if(getGame().getState() != this) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(getPlugin().getGameManager().findGame(event.getPlayer())!=getGame()) return;
        if(getGame().getState() != this) return;

        if(event.getItem().getType() == Material.COMPASS){
            getPlugin().getGUIAPI().openGUI(event.getPlayer(),new TeamSelectorGUI(getPlugin(),event.getPlayer()));
        }

    }

}
