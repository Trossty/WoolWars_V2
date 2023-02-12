package woolwars.woolwars.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public class GameClass {

    private String name;
    private ItemStack guiItem;
    private String permission;

}
