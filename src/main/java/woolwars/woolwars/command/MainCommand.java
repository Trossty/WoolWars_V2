package woolwars.woolwars.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.objects.guis.PlayGUI;
import woolwars.woolwars.utils.Colorize;

public class MainCommand implements CommandExecutor {

    private WoolWarsPlugin plugin;

    public MainCommand(WoolWarsPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(args[0].equalsIgnoreCase("play")){

            getPlugin().getGUIAPI().openGUI(player,new PlayGUI(getPlugin()));

            return false;
        }

        if(args[0].equalsIgnoreCase("admin")){

            if(args[1].equalsIgnoreCase("set")){

                if(args[2].equalsIgnoreCase("location")){

                    Game game = getPlugin().getGameManager().findGame(player);

                    if(game == null){
                        Colorize.sendMessage(player,"&cYou should be in game!");
                        return false;
                    }

                    if(game.getMap().isLocationsReady()){
                        Colorize.sendMessage(player,"&cLocations are already set!");
                        return false;
                    }



                    switch (args[3]){

                        case "RedSpawn":
                            break;
                        case "BlueSpawn":
                            break;
                        case "RedShopSpawn":
                            break;
                        case "BlueShopSpawn":
                            break;
                        case "Center":
                            break;
                        case "Upgrades":
                            break;

                    }

                }

            }

        }

        return false;
    }

    public WoolWarsPlugin getPlugin() {
        return plugin;
    }
}
