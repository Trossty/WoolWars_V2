package woolwars.woolwars.worldedit;

import com.fastasyncworldedit.core.FaweAPI;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.io.*;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import lombok.SneakyThrows;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.*;

public class WorldEditHandler {

    @SneakyThrows
    public static void paste(File file, Location location){

        ClipboardFormat format = ClipboardFormats.findByFile(file);

        ClipboardReader reader = format.getReader(new FileInputStream(file));

        com.sk89q.worldedit.extent.clipboard.Clipboard clipboard = reader.read();

        com.sk89q.worldedit.world.World adaptedWorld = FaweAPI.getWorld(location.getWorld().getName());

        EditSession editSession = WorldEdit.getInstance().newEditSessionBuilder().world(adaptedWorld).maxBlocks(-1).fastMode(false).build();


        int X = location.getBlockX();
        int Y = location.getBlockY();
        int Z = location.getBlockZ();

        Operation operation = new ClipboardHolder(clipboard).createPaste(editSession)
                .to(BlockVector3.at(X, Y, Z)).build();

        try {
            Operations.complete(operation);
            editSession.flushSession();

        } catch (WorldEditException e) {
            e.printStackTrace();
        }

    }

    public static void copy(File destination, Location min, Location max){

        World world = min.getWorld();

        CuboidRegion region = new CuboidRegion(FaweAPI.getWorld(world.getName()), BlockVector3.at(min.getX(),min.getY(),min.getZ()), BlockVector3.at(max.getX(), max.getY(), max.getZ()));
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);

        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(region.getWorld(), -1);

        ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());
        forwardExtentCopy.setCopyingEntities(true);
        Operations.complete(forwardExtentCopy);

        try (ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(destination))) {
            writer.write(clipboard);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}