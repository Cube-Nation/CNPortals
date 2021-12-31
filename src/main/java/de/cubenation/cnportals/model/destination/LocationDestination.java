package de.cubenation.cnportals.model.destination;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class LocationDestination extends AbstractDestination {

    private Location location;

    public LocationDestination(Player player) {
        this.setPlayerLocation(player);
    }

    public LocationDestination(World world, double x, double y, double z, float pitch, float yaw) {
        this.setLocation(new Location(world, x, y, z, yaw, pitch));
    }

    public LocationDestination(Location location) {
        this.setLocation(location);
    }

    @Override
    public Location getLocation(Location currentLocation) {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPlayerLocation(Player player) {
        this.setLocation(player.getLocation());
    }

    public World getWorld() {
        return this.location.getWorld();
    }

    public double getX() {
        return this.location.getX();
    }

    public double getY() {
        return this.location.getY();
    }

    public double getZ() {
        return this.location.getZ();
    }

    public float getPitch() {
        return this.location.getPitch();
    }

    public float getYaw() {
        return this.location.getYaw();
    }
}
