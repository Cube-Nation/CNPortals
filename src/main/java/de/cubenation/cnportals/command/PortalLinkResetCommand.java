package de.cubenation.cnportals.command;

import de.cubenation.api.bedrock.BasePlugin;
import de.cubenation.api.bedrock.annotation.*;
import de.cubenation.api.bedrock.command.Command;
import de.cubenation.api.bedrock.command.CommandRole;
import de.cubenation.api.bedrock.service.command.CommandManager;
import de.cubenation.cnportals.config.PortalConfig;
import de.cubenation.cnportals.messages.Messages;
import de.cubenation.cnportals.model.Portal;
import de.cubenation.cnportals.model.destination.LocationDestination;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Description("command.portal.link.reset.desc")
@SubCommand({"link"})
@SubCommand({"reset"})
@Argument(
        Description = "command.portal.link.reset.args.portalid.desc",
        Placeholder = "command.portal.link.reset.args.portalid.ph"
)
@Permission(Name = "portal.link.reset", Role = CommandRole.MODERATOR)
@IngameCommand
public class PortalLinkResetCommand extends Command {

    public PortalLinkResetCommand(BasePlugin plugin, CommandManager commandManager) {
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

        boolean success = config.updateDestination(id, null);
        if(success)
            Messages.Success.PortalLinkReset(player, id);
        else
            Messages.Failure.PortalLinkReset(player, id);
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
