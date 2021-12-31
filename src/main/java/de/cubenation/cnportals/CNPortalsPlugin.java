package de.cubenation.cnportals;

import de.cubenation.api.bedrock.BasePlugin;
import de.cubenation.api.bedrock.annotation.CommandHandler;
import de.cubenation.api.bedrock.annotation.ConfigurationFile;
import de.cubenation.api.bedrock.annotation.Service;
import de.cubenation.api.bedrock.command.CommandRole;
import de.cubenation.cnportals.command.*;
import de.cubenation.cnportals.config.PortalConfig;
import de.cubenation.cnportals.config.locale.de_DE;
import de.cubenation.cnportals.listener.PlayerListener;
import de.cubenation.cnportals.service.PortalPageableListService;
import de.cubenation.cnportals.service.PortalService;
import de.cubenation.cnportals.util.ExtraPermissions;

@CommandHandler(Command = "portal", Handlers = {
        PortalDefineCommand.class,
        PortalLinkLocationCommand.class,
        PortalLinkPortalCommand.class,
        PortalLinkResetCommand.class,
        PortalListCommand.class,
        PortalRedefineCommand.class,
        PortalRemoveCommand.class,
        PortalSelectCommand.class
})
@ConfigurationFile(PortalConfig.class)
@ConfigurationFile(de_DE.class)
@Service(PortalService.class)
public class CNPortalsPlugin extends BasePlugin {

    private static CNPortalsPlugin instance;

    public static CNPortalsPlugin getInstance() {
        return instance;
    }

    @Override
    public void onPreEnable() {
        instance = this;
    }

    @Override
    protected void onPostEnable() {
        this.getPermissionService().registerPermission(ExtraPermissions.PORTAL_USE, CommandRole.USER);

        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    public PortalService getPortalService() {
        return (PortalService) getServiceManager().getService(PortalService.class);
    }
}
