package woolwars.woolwars.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Colorize {
    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static void sendMessage(Player player, String msg){
        player.sendMessage(format(msg));
    }

    public static String format(String text){
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            String color = text.substring(matcher.start(),matcher.end());
            text = text.replace(color, ChatColor.of(color) +"");
            matcher = pattern.matcher(text);
        }
        return ChatColor.translateAlternateColorCodes('&',text);
    }

    public static String strip(String text){
        return ChatColor.stripColor(text);
    }
}
