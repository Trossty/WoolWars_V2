package woolwars.woolwars.objects.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.api.GUI.GUI;
import woolwars.woolwars.utils.Colorize;
import woolwars.woolwars.utils.ItemBuilder;

public class PlayGUI extends GUI<WoolWarsPlugin> {
    public PlayGUI(WoolWarsPlugin plugin) {
        super(plugin);

        createInventory();
    }

    private void createInventory() {

        set(11,new ItemBuilder(Material.DIAMOND_AXE).withDisplayName("&aPlay!").getItemStack());

        set(15,new ItemBuilder(Material.MAP).withDisplayName("&2Browse Maps").getItemStack());

    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public String getTitle() {
        return Colorize.format("&6&lWoolWars &7- &bPlay");
    }

    @Override
    public boolean canClose(Player player) {
        return true;
    }
}
