package de.cubenation.cnportals.model;

import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.Location;
import org.bukkit.World;

public class PortalDimensions {

    private World world;
    private Double xMin;
    private Double yMin;
    private Double zMin;
    private Double xMax;
    private Double yMax;
    private Double zMax;

    public PortalDimensions(Location from, Location to) {
        this(from.getWorld(),
                from.getX(), from.getY(), from.getZ(),
                to.getX(), to.getY(), to.getZ());
    }

    public PortalDimensions(World world,
            Double fromX, Double fromY, Double fromZ,
            Double toX, Double toY, Double toZ) {

        this.world = world;
        this.xMin = fromX <= toX ? fromX : toX;
        this.yMin = fromY <= toY ? fromY : toY;
        this.zMin = fromZ <= toZ ? fromZ : toZ;
        this.xMax = toX >= fromX ? toX : fromX;
        this.yMax = toY >= fromY ? toY : fromY;
        this.zMax = toZ >= fromZ ? toZ : fromZ;
    }

    public PortalDimensions(World world, BlockVector3 minimumPoint, BlockVector3 maximumPoint) {
        this(
                new Location(world, minimumPoint.getBlockX(), minimumPoint.getBlockY(), minimumPoint.getBlockZ()),
                new Location(world, maximumPoint.getBlockX(), maximumPoint.getBlockY(), maximumPoint.getBlockZ())
        );
    }

    // Implementation

    public Location getMinLocation() {
        return new Location(world, xMin, yMin, zMin);
    }

    public Location getMaxLocation() {
        return new Location(world, xMax + 1, yMax + 1, zMax + 1);
    }

    public Location getCenterLocation() {
        return getMinLocation().add(getMaxLocation().subtract(getMinLocation()).multiply(0.5));
    }

    public Location getTeleportLocation() {
        Location centerLocation = getCenterLocation();
        centerLocation.setY(getMinLocation().getBlockY());
        return centerLocation;
    }

    public Location getTeleportLocation(float pitch, float yaw) {
        Location centerLocation = getTeleportLocation();
        centerLocation.setPitch(pitch);
        centerLocation.setYaw(yaw);
        return centerLocation;
    }


    // Getter & Setter

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Double getxMin() {
        return xMin;
    }

    public void setXMin(Double xMin) {
        this.xMin = xMin;
    }

    public Double getYMin() {
        return yMin;
    }

    public void setYMin(Double yMin) {
        this.yMin = yMin;
    }

    public Double getZMin() {
        return zMin;
    }

    public void setZMin(Double zMin) {
        this.zMin = zMin;
    }

    public Double getXMax() {
        return xMax;
    }

    public void setXMax(Double xMax) {
        this.xMax = xMax;
    }

    public Double getYMax() {
        return yMax;
    }

    public void setYMax(Double yMax) {
        this.yMax = yMax;
    }

    public Double getZMax() {
        return zMax;
    }

    public void setZMax(Double zMax) {
        this.zMax = zMax;
    }

    @Override
    public String toString() {
        return "PortalDimensions{" +
                ", world=" + world +
                ", xMin=" + xMin +
                ", yMin=" + yMin +
                ", zMin=" + zMin +
                ", xMax=" + xMax +
                ", yMax=" + yMax +
                ", zMax=" + zMax +
                '}';
    }
}
