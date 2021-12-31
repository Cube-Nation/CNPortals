package de.cubenation.cnportals.command;

import de.cubenation.api.bedrock.BasePlugin;
import de.cubenation.api.bedrock.annotation.*;
import de.cubenation.api.bedrock.command.Command;
import de.cubenation.api.bedrock.command.CommandRole;
import de.cubenation.api.bedrock.service.command.CommandManager;
import de.cubenation.cnportals.config.PortalConfig;
import de.cubenation.cnportals.messages.Messages;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Description("command.portal.list.desc")
@SubCommand({"list", "ls"})
@Argument(
        Description = "command.portal.list.args.page.desc",
        Placeholder = "command.portal.list.args.page.ph",
        Optional = true
)
@Permission(Name = "portal.list", Role = CommandRole.MODERATOR)
public class PortalListCommand extends Command {

    public PortalListCommand(BasePlugin plugin, CommandManager commandManager) {
        super(plugin, commandManager);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        PortalConfig config = (PortalConfig) plugin.getConfigService().getConfig(PortalConfig.class);
        List<de.cubenation.cnportals.model.Portal> portals = config.getPortals().stream().sorted().collect(Collectors.toList());

        if (portals.isEmpty()) {
            Messages.Portals.ListEmpty(commandSender);
            return;
        }

        int number = 1;
        if (args.length > 0 && StringUtils.isNumeric(args[0])) {
            number = Integer.parseInt(args[0]);
        }

        Messages.Portals.ListNonEmpty(commandSender, portals, number, "/" + getCommandManager().getPluginCommand().getLabel() + " list %page%");
    }

    @Override
    public ArrayList<String> getTabArgumentCompletion(CommandSender sender, int argumentIndex, String[] args) {
        if (argumentIndex == 0) {
            PortalConfig config = (PortalConfig) plugin.getConfigService().getConfig(PortalConfig.class);
            return new ArrayList<>(config.getPortals().stream().map(de.cubenation.cnportals.model.Portal::getId).collect(Collectors.toList()));
        }

        return null;
    }
}
