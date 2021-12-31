package de.cubenation.cnportals.command;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.regions.Region;
import de.cubenation.api.bedrock.BasePlugin;
import de.cubenation.api.bedrock.annotation.*;
import de.cubenation.api.bedrock.command.Command;
import de.cubenation.api.bedrock.command.CommandRole;
import de.cubenation.api.bedrock.helper.MessageHelper;
import de.cubenation.api.bedrock.service.command.CommandManager;
import de.cubenation.cnportals.config.PortalConfig;
import de.cubenation.cnportals.messages.Messages;
import de.cubenation.cnportals.model.Portal;
import de.cubenation.cnportals.model.PortalDimensions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Description("command.portal.define.desc")
@SubCommand({"define", "set"})
@Argument(
        Description = "command.portal.define.args.portalid.desc",
        Placeholder = "command.portal.define.args.portalid.ph"
)
@Permission(Name = "portal.define", Role = CommandRole.MODERATOR)
@IngameCommand
public class PortalDefineCommand extends Command {

    public PortalDefineCommand(BasePlugin plugin, CommandManager commandManager) {
        super(plugin, commandManager);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;

        String id = args[0];

        PortalConfig config = (PortalConfig) plugin.getConfigService().getConfig(PortalConfig.class);

        if(config.isPortalExistent(id)) {
            Messages.Error.PortalIdAlreadyExistent(player, id);
            return;
        }

        boolean success = createZoneWithWE(config, player, id);
        if(success) {
            Messages.Success.PortalDefine(player, id);
            Messages.Portals.PostCreationHints(commandSender, id);
        } else {
            Messages.Failure.PortalDefine(player, id);
        }
    }

    private boolean createZoneWithWE(PortalConfig config, Player player, String id) {
        BukkitPlayer bPlayer = BukkitAdapter.adapt(player);
        Region selection = null;
        try {
            selection = WorldEdit.getInstance().getSessionManager().get(bPlayer).getSelection(bPlayer.getWorld());
        } catch (IncompleteRegionException e) {
            selection = null;
        }
        if (selection == null) {
            Messages.Error.ErrorNoWESelection(player);
            return false;
        }

        PortalDimensions dimensions = new PortalDimensions(player.getWorld(), selection.getMinimumPoint(), selection.getMaximumPoint());
        Portal portal = new Portal(id, dimensions);

        return config.storePortal(portal);
    }
}
