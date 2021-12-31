package de.cubenation.cnportals.manager;

import de.cubenation.cnportals.CNPortalsPlugin;
import de.cubenation.cnportals.helper.LocationHelper;
import de.cubenation.cnportals.model.Portal;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PortalAreaManager {

    private static PortalAreaManager instance;

    public static PortalAreaManager getInstance() {
        if(instance == null)
            instance = new PortalAreaManager();
        return instance;
    }

    private ArrayList<UUID> inPortalPlayers = new ArrayList<>();

    public Portal getPortalForLocation(Location playerLocation) {
        ArrayList<Portal> possibleLocations = CNPortalsPlugin.getInstance().getPortalService().getPortals(playerLocation.getWorld());

        if(possibleLocations == null)
            return null;

        for (Portal possibleLocation : possibleLocations) {
            if (LocationHelper.isBetweenLocations(playerLocation, possibleLocation.getDimensions().getMinLocation(), possibleLocation.getDimensions().getMaxLocation())) {
                return possibleLocation;
            }
        }

        return null;
    }

    public boolean tryAddToInPortalPlayers(Player player) {
        if(inPortalPlayers.contains(player.getUniqueId()))
            return false;
        inPortalPlayers.add(player.getUniqueId());
        return true;
    }

    public boolean tryRemoveFromInPortalPlayers(Player player) {
        if(!inPortalPlayers.contains(player.getUniqueId()))
            return false;
        inPortalPlayers.remove(player.getUniqueId());
        return true;
    }
}
