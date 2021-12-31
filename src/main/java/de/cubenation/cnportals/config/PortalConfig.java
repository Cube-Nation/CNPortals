package de.cubenation.cnportals.config;

import de.cubenation.api.bedrock.BasePlugin;
import de.cubenation.api.bedrock.service.config.CustomConfigurationFile;
import de.cubenation.cnportals.CNPortalsPlugin;
import de.cubenation.cnportals.model.Portal;
import de.cubenation.cnportals.model.PortalDimensions;
import de.cubenation.cnportals.model.converter.PortalConverter;
import de.cubenation.cnportals.model.destination.AbstractDestination;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.cubespace.Yamler.Config.InvalidConverterException;
import net.cubespace.Yamler.Config.Path;

import java.io.File;
import java.util.ArrayList;

public class PortalConfig extends CustomConfigurationFile {


    public static String getFilename() {
        return "portals.yaml";
    }

    public PortalConfig(BasePlugin plugin) {
        this(plugin, getFilename());
    }

    public PortalConfig(BasePlugin plugin, String filename) {
        CONFIG_FILE = new File(plugin.getDataFolder(), filename);
        try {
            addConverter(PortalConverter.class);
        } catch (InvalidConverterException e) {
            e.printStackTrace();
        }
    }

    @Comment("List of stored portals")
    @Path("portals")
    public ArrayList<Portal> portals = new ArrayList<>();

    public ArrayList<Portal> getPortals() {
        return portals;
    }

    public boolean storePortal(Portal portal) {
        portals.add(portal);
        return saveAndReload();
    }

    public boolean removePortal(String id) {
        boolean success = this.portals.removeIf(x -> x.getId().equals(id));

        if(!success)
            return false;

        return saveAndReload();
    }

    public Portal getPortal(String id) {
        try {
            return this.portals.stream()
                    .filter(x -> x.getId().equals(id))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean isPortalExistent(String id) {
        return this.getPortal(id) != null;
    }

    public boolean updateDestination(String id, AbstractDestination destination) {
        try {
            this.portals.stream()
                    .filter(x -> x.getId().equals(id))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new)
                    .setDestination(destination);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return saveAndReload();
    }

    public boolean updateDimensions(String id, PortalDimensions dimensions) {
        try {
            this.portals.stream()
                    .filter(x -> x.getId().equals(id))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new)
                    .setDimensions(dimensions);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return saveAndReload();
    }

    private boolean saveAndReload() {
        try {
            this.save();
            this.reload();

            CNPortalsPlugin.getInstance().getPortalService().reload();

            return true;
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            return false;
        }
    }
}
