package woolwars.woolwars.objects.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.api.GUI.GUI;
import woolwars.woolwars.classes.GameTeam;
import woolwars.woolwars.game.GamePlayer;
import woolwars.woolwars.utils.Colorize;
import woolwars.woolwars.utils.ItemBuilder;

public class TeamSelectorGUI extends GUI<WoolWarsPlugin> {
    public TeamSelectorGUI(WoolWarsPlugin plugin) {
        super(plugin);

        createInventory();
    }

    private void createInventory() {

        set(11, new ItemBuilder(Material.RED_WOOL).withDisplayName("&cRed Team").withLore(" ","&aClick to Join!"," ").getItemStack(),(whoClicked, clickedItem) -> {

            GamePlayer gamePlayer = GamePlayer.getGamePlayer(whoClicked.getUniqueId()).get();

            if(gamePlayer.getTeam().equals(GameTeam.RED)){

                Colorize.sendMessage(whoClicked,"&cYou already in RED team!");

                return ButtonAction.CLOSE_GUI;
            }

            gamePlayer.setTeam(GameTeam.RED);

            Colorize.sendMessage(whoClicked, "&aYou are in &cRED &a team!");

            return ButtonAction.CLOSE_GUI;
        });

        set(15, new ItemBuilder(Material.BLUE_WOOL).withDisplayName("&bBlue Team").withLore(" ","&aClick to Join!"," ").getItemStack(),(whoClicked, clickedItem) -> {

            GamePlayer gamePlayer = GamePlayer.getGamePlayer(whoClicked.getUniqueId()).get();

            if(gamePlayer.getTeam().equals(GameTeam.BLUE)){

                Colorize.sendMessage(whoClicked,"&cYou already in &bBLUE&c team!");

                return ButtonAction.CLOSE_GUI;
            }

            gamePlayer.setTeam(GameTeam.BLUE);

            Colorize.sendMessage(whoClicked, "&aYou are in &bBLUE &ateam!");

            return ButtonAction.CLOSE_GUI;
        });

    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public String getTitle() {
        return "&6&lWoolWars &7- &bTeam Selector";
    }

    @Override
    public boolean canClose(Player player) {
        return true;
    }
}
