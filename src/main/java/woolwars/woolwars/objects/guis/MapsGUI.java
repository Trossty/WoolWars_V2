package woolwars.woolwars.objects.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import woolwars.woolwars.WoolWarsPlugin;
import woolwars.woolwars.api.GUI.GUI;
import woolwars.woolwars.utils.ItemBuilder;

public class MapsGUI extends GUI<WoolWarsPlugin> {


    public MapsGUI(WoolWarsPlugin plugin) {
        super(plugin);

        createInventory();
    }

    private void createInventory() {

        final int[] i = {0};
        getPlugin().getGameManager().getMapList().forEach(map -> {

            set(i[0],new ItemBuilder(Material.FILLED_MAP).withDisplayName("&b"+map.getMapName()).getItemStack(),(whoClicked, clickedItem) -> {
                getPlugin().getGameManager().placePlayerSelectedMap(whoClicked,map);
                return ButtonAction.CLOSE_GUI;
            });

            i[0]++;
        });

    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public String getTitle() {
        return "&6&lWoolWars &7- &bMaps";
    }

    @Override
    public boolean canClose(Player player) {
        return true;
    }
}
