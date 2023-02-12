package woolwars.woolwars.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import woolwars.woolwars.classes.GameTeam;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class GamePlayer {

    @Getter
    @Setter
    private GameTeam team;

    @Getter
    private UUID playerUUID;

    @Getter
    @Setter
    private int killCount = 0;
    @Getter
    @Setter
    private int blocksPlaced = 0;
    @Getter
    @Setter
    private int blocksBreaked = 0;
    @Getter
    @Setter
    private GameClass gameClass;

    private static final HashMap<UUID, GamePlayer> gamePlayerHashMap = new HashMap<>();


    public GamePlayer(UUID uuid){

        playerUUID = uuid;
        team = GameTeam.NONE;

        gamePlayerHashMap.put(uuid,this);
    }


    public static Optional<GamePlayer> getGamePlayer(Player player){
        return getGamePlayer(player.getUniqueId());
    }

    public static Optional<GamePlayer> getGamePlayer(UUID uuid){
        if(!gamePlayerHashMap.containsKey(uuid)){
            gamePlayerHashMap.put(uuid,new GamePlayer(uuid));
        }
        return Optional.of(gamePlayerHashMap.get(uuid));
    }

    public static void removePlayer(UUID uuid){

        GamePlayer gamePlayer = getGamePlayer(uuid).get();

        gamePlayer.setTeam(GameTeam.NONE);

        gamePlayerHashMap.remove(uuid);
    }

}
