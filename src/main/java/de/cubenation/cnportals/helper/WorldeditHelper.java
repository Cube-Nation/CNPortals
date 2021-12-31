package de.cubenation.cnportals.helper;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import de.cubenation.cnportals.config.PortalConfig;
import de.cubenation.cnportals.model.Portal;
import de.cubenation.cnportals.model.PortalDimensions;
import de.cubenation.cnportals.model.destination.LocationDestination;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WorldeditHelper {

    public static BlockVector3 transformToBlockVector3(Location loc) {
        return BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    public static boolean selectWithWe(Player player, Location minLoc, Location maxLoc) {
        if(!minLoc.getWorld().getUID().equals(maxLoc.getWorld().getUID())) {
            throw new IllegalArgumentException("world UUID mismatch");
        }
        World world = BukkitAdapter.adapt(minLoc.getWorld());
        BukkitPlayer bPlayer = BukkitAdapter.adapt(player);

        return selectWithWe(bPlayer, world, transformToBlockVector3(minLoc), transformToBlockVector3(maxLoc));
    }

    public static boolean selectWithWe(BukkitPlayer bPlayer, World world, BlockVector3 minPos, BlockVector3 maxPos) {
        try {
            WorldEdit.getInstance().getSessionManager().get(bPlayer). = new CuboidRegion(world, minPos, maxPos);
            //WorldEdit.getInstance().getSessionManager().get(bPlayer).getSelection(world).getBoundingBox().setPos1(minPos);
            //WorldEdit.getInstance().getSessionManager().get(bPlayer).getSelection(world).getBoundingBox().setPos2(maxPos);
        } catch (IncompleteRegionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
