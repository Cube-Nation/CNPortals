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
import de.cubenation.cnportals.model.destination.PortalDestination;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Description("command.portal.link.portal.desc")
@SubCommand({"link"})
@SubCommand({"portal"})
@Argument(
        Description = "command.portal.link.portal.args.portalidfrom.desc",
        Placeholder = "command.portal.link.portal.args.portalidfrom.ph"
)
@Argument(
        Description = "command.portal.link.portal.args.portalidto.desc",
        Placeholder = "command.portal.link.portal.args.portalidto.ph"
)
@Permission(Name = "portal.link.portal", Role = CommandRole.MODERATOR)
@IngameCommand
public class PortalLinkPortalCommand extends Command {

    public PortalLinkPortalCommand(BasePlugin plugin, CommandManager commandManager) {
        super(plugin, commandManager);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;

        String idFrom = args[0];
        String idTo = args[1];

        PortalConfig config = (PortalConfig) plugin.getConfigService().getConfig(PortalConfig.class);

        if(!config.isPortalExistent(idFrom)) {
            Messages.Error.PortalIdNotExistent(player, idFrom);
            return;
        }

        if(!config.isPortalExistent(idTo)) {
            Messages.Error.PortalIdNotExistent(player, idTo);
            return;
        }

        PortalDestination dest = new PortalDestination(idTo);

        boolean success = config.updateDestination(idFrom, dest);
        if(success)
            Messages.Success.PortalLinkPortal(player, idFrom, idTo);
        else
            Messages.Failure.PortalLinkPortal(player, idFrom, idTo);
    }

    @Override
    public ArrayList<String> getTabArgumentCompletion(CommandSender sender, int argumentIndex, String[] args) {
        if (argumentIndex == 0 || argumentIndex == 1) {
            PortalConfig config = (PortalConfig) plugin.getConfigService().getConfig(PortalConfig.class);
            return new ArrayList<>(config.getPortals().stream().map(Portal::getId).collect(Collectors.toList()));
        }

        return null;
    }
}
