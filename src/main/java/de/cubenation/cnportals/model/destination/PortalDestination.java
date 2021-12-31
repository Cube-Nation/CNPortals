package de.cubenation.cnportals.model.destination;

import de.cubenation.cnportals.CNPortalsPlugin;
import de.cubenation.cnportals.config.PortalConfig;
import de.cubenation.cnportals.model.Portal;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PortalDestination extends AbstractDestination {

    private String portalId;

    public PortalDestination(Portal portal) {
        this.setPortal(portal);
    }

    public PortalDestination(String portalId) {
        this.setPortalId(portalId);
    }

    @Override
    public Location getLocation(Location currentLocation) {
        PortalConfig config = (PortalConfig) CNPortalsPlugin.getInstance().getConfigService().getConfig(PortalConfig.class);
        Portal portal = config.getPortal(portalId);

        if(currentLocation == null)
            return portal.getDimensions().getTeleportLocation();
        return portal.getDimensions().getTeleportLocation(currentLocation.getPitch(), currentLocation.getYaw());
    }

    public String getPortalId() {
        return portalId;
    }

    public void setPortalId(String portalId) {
        this.portalId = portalId;
    }

    public void setPortal(Portal portal) {
        this.portalId = portal.getId();
    }
}
