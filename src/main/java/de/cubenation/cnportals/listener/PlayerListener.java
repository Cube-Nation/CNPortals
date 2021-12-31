package de.cubenation.cnportals.listener;

import de.cubenation.cnportals.CNPortalsPlugin;
import de.cubenation.cnportals.manager.PortalAreaManager;
import de.cubenation.cnportals.messages.Messages;
import de.cubenation.cnportals.model.Portal;
import de.cubenation.cnportals.util.ExtraPermissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        // Check for already cancelled events
        if(e.isCancelled())
            return;

        // Only trigger if moved more than a block
        if(e.getFrom().getBlockX() == e.getTo().getBlockX() && e.getFrom().getBlockY() == e.getTo().getBlockY() && e.getFrom().getBlockZ() == e.getTo().getBlockZ())
            return;

        Portal portal = PortalAreaManager.getInstance().getPortalForLocation(e.getTo());
        if(portal == null) {
            PortalAreaManager.getInstance().tryRemoveFromInPortalPlayers(player);
            return;
        }

        boolean success = PortalAreaManager.getInstance().tryAddToInPortalPlayers(player);
        if(!success)
            return; // Already in portal

        if(portal.getDestination() == null) {
            Messages.Action.NoPortalDestination(player);
            return;
        }

        if(!CNPortalsPlugin.getInstance().getPermissionService().hasPermission(player, ExtraPermissions.PORTAL_USE)) {
            Messages.Action.NoPortalUsePermission(player);
            return;
        }

        CNPortalsPlugin.getInstance().getPortalService().executeTeleport(portal, player);
    }
}
