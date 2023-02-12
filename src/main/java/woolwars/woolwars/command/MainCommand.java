package woolwars.woolwars.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.objects.guis.PlayGUI;

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

        return false;
    }

    public WoolWarsPlugin getPlugin() {
        return plugin;
    }
}
