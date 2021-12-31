package de.cubenation.cnportals.service;

import de.cubenation.api.bedrock.BasePlugin;
import de.cubenation.api.bedrock.exception.ServiceInitException;
import de.cubenation.api.bedrock.exception.ServiceReloadException;
import de.cubenation.api.bedrock.service.AbstractService;
import de.cubenation.cnportals.config.PortalConfig;
import de.cubenation.cnportals.helper.LocationHelper;
import de.cubenation.cnportals.model.Portal;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PortalService extends AbstractService {

    private HashMap<World, ArrayList<Portal>> portalsPerWorld = new HashMap<>();

    public PortalService(BasePlugin plugin) {
        super(plugin);
    }

    @Override
    public void init() {
        this.reload();
    }

    @Override
    public void reload() {
        portalsPerWorld.clear();
        PortalConfig config = (PortalConfig) plugin.getConfigService().getConfig(PortalConfig.class);
        setPortalsPerWorld(config);
    }

    private void setPortalsPerWorld(PortalConfig config) {
        HashMap<World, ArrayList<Portal>> list = new HashMap<>();

        for (Portal portal : config.getPortals()) {
            ArrayList<Portal> teleporters = null;
            if (list.containsKey(portal.getDimensions().getWorld())) {
                teleporters = list.get(portal.getDimensions().getWorld());
            } else {
                teleporters = new ArrayList<>();
            }
            teleporters.add(portal);
            list.put(portal.getDimensions().getWorld(), teleporters);
        }

        portalsPerWorld = list;
    }

    public ArrayList<Portal> getPortals(World world) {
        return portalsPerWorld.get(world);
    }

    public void executeTeleport(Portal portal, Player player) {
        Location loc = portal.getDestination().getLocation(player.getLocation());
        player.teleport(loc);
    }
}
