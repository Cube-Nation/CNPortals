package de.cubenation.cnportals.command;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import de.cubenation.api.bedrock.BasePlugin;
import de.cubenation.api.bedrock.annotation.*;
import de.cubenation.api.bedrock.command.Command;
import de.cubenation.api.bedrock.command.CommandRole;
import de.cubenation.api.bedrock.service.command.CommandManager;
import de.cubenation.cnportals.CNPortalsPlugin;
import de.cubenation.cnportals.config.PortalConfig;
import de.cubenation.cnportals.helper.WorldeditHelper;
import de.cubenation.cnportals.messages.Messages;
import de.cubenation.cnportals.model.Portal;
import de.cubenation.cnportals.model.PortalDimensions;
import jdk.nashorn.internal.ir.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Description("command.portal.select.desc")
@SubCommand({"select"})
@Argument(
        Description = "command.portal.select.args.portalid.desc",
        Placeholder = "command.portal.select.args.portalid.ph"
)
@Permission(Name = "portal.select", Role = CommandRole.MODERATOR)
@IngameCommand
public class PortalSelectCommand extends Command {

    public PortalSelectCommand(BasePlugin plugin, CommandManager commandManager) {
        super(plugin, commandManager);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;

        String id = args[0];

        PortalConfig config = (PortalConfig) plugin.getConfigService().getConfig(PortalConfig.class);

        if(!config.isPortalExistent(id)) {
            Messages.Error.PortalIdNotExistent(player, id);
            return;
        }

        Portal portal = config.getPortal(id);
        PortalDimensions dimensions = portal.getDimensions();

        boolean success = WorldeditHelper.selectWithWe(player, dimensions.getMinLocation(), dimensions.getMaxLocation());
        if(success) {
            Messages.Success.PortalSelect(player, id);
        } else {
            Messages.Failure.PortalSelect(player, id);
        }
    }

    @Override
    public ArrayList<String> getTabArgumentCompletion(CommandSender sender, int argumentIndex, String[] args) {
        if (argumentIndex == 0) {
            PortalConfig config = (PortalConfig) plugin.getConfigService().getConfig(PortalConfig.class);
            return new ArrayList<>(config.getPortals().stream().map(Portal::getId).collect(Collectors.toList()));
        }

        return null;
    }
}
