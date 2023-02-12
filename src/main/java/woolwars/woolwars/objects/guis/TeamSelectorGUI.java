package woolwars.woolwars.objects.guis;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.api.GUI.GUI;
import woolwars.woolwars.classes.GameTeam;
import woolwars.woolwars.game.Game;
import woolwars.woolwars.game.GameClass;
import woolwars.woolwars.game.GamePlayer;
import woolwars.woolwars.utils.Colorize;
import woolwars.woolwars.utils.ItemBuilder;

public class TeamSelectorGUI extends GUI<WoolWarsPlugin> {

    private final Player player;

    public TeamSelectorGUI(WoolWarsPlugin plugin, Player player) {
        super(plugin);

        this.player = player;

        createInventory();
    }

    private void createInventory() {

        GamePlayer gamePlayer = GamePlayer.getGamePlayer(player).get();

        Game game = getPlugin().getGameManager().findGame(player);

        if(game == null){
            return;
        }

        ItemBuilder blue = new ItemBuilder(Material.BLUE_WOOL).withDisplayName("&bBlue Team").withLore(" ","&aClick to Join!"," ");
        ItemBuilder red = new ItemBuilder(Material.RED_WOOL).withDisplayName("&cRed Team").withLore(" ","&aClick to Join!"," ");

        if(gamePlayer.getTeam() == GameTeam.BLUE){
            blue.withEnchant(Enchantment.CHANNELING,1,false).withFlags(ItemFlag.HIDE_ENCHANTS);
        }else if(gamePlayer.getTeam() == GameTeam.RED){
            red.withEnchant(Enchantment.CHANNELING,1,false).withFlags(ItemFlag.HIDE_ENCHANTS);
        }

        set(11, red.getItemStack(),(whoClicked, clickedItem) -> {

            if(gamePlayer.getTeam().equals(GameTeam.RED)){

                Colorize.sendMessage(whoClicked,"&cYou are already in RED team!");

                return ButtonAction.CLOSE_GUI;
            }

            gamePlayer.setTeam(GameTeam.RED);

            game.getBluePlayerList().remove(player.getUniqueId());

            game.getRedPlayerList().add(player.getUniqueId());

            Colorize.sendMessage(whoClicked, "&aJoined &cRED&a team!");

            return ButtonAction.CLOSE_GUI;
        });


        set(15, blue.getItemStack(),(whoClicked, clickedItem) -> {

            if(gamePlayer.getTeam().equals(GameTeam.BLUE)){

                Colorize.sendMessage(whoClicked,"&cYou are already in &bBLUE&c team!");

                return ButtonAction.CLOSE_GUI;
            }

            gamePlayer.setTeam(GameTeam.BLUE);

            game.getRedPlayerList().remove(player.getUniqueId());

            game.getBluePlayerList().add(player.getUniqueId());

            Colorize.sendMessage(whoClicked, "&aJoined &bBLUE&a team!");

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
