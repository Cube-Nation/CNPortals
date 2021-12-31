package de.cubenation.cnportals.model.converter;

import de.cubenation.cnportals.model.Portal;
import de.cubenation.cnportals.model.PortalDimensions;
import de.cubenation.cnportals.model.destination.AbstractDestination;
import de.cubenation.cnportals.model.destination.LocationDestination;
import de.cubenation.cnportals.model.destination.PortalDestination;
import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.Converter.Converter;
import net.cubespace.Yamler.Config.InternalConverter;
import org.bukkit.Bukkit;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public class PortalConverter implements Converter {

    private final String ID = "id";
    private final String DIMENSIONS = "dimensions";
    private final String DESTINATION = "destination";

    private final String DIMENSIONS_WORLDUUID = "worlduuid";
    private final String DIMENSIONS_WORLDNAME = "worldname";
    private final String DIMENSIONS_XMIN = "xMin";
    private final String DIMENSIONS_YMIN = "yMin";
    private final String DIMENSIONS_ZMIN = "zMin";
    private final String DIMENSIONS_XMAX = "xMax";
    private final String DIMENSIONS_YMAX = "yMax";
    private final String DIMENSIONS_ZMAX = "zMax";

    private final String DESTINATION_TYPE = "type";
    private final String DESTINATION_LOCATION_WORLDUUID = "worlduuid";
    private final String DESTINATION_LOCATION_WORLDNAME = "worldname";
    private final String DESTINATION_LOCATION_X = "x";
    private final String DESTINATION_LOCATION_Y = "y";
    private final String DESTINATION_LOCATION_Z = "z";
    private final String DESTINATION_LOCATION_PITCH = "pitch";
    private final String DESTINATION_LOCATION_YAW = "yaw";
    private final String DESTINATION_PORTAL_PORTALID = "portalid";

    public PortalConverter(InternalConverter converter) {}

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType genericType) throws Exception {
        Portal portal = (Portal) obj;
        PortalDimensions dimensions = portal.getDimensions();
        AbstractDestination destination = portal.getDestination();
        Map<String, Object> saveMap = new HashMap<>();
        saveMap.put(ID, portal.getId());

        Map<String, Object> dimensionsMap = new HashMap<>();
        if (dimensions.getWorld() == null) {
            dimensionsMap.put(DIMENSIONS_WORLDUUID, null);
            dimensionsMap.put(DIMENSIONS_WORLDNAME, null);
        } else {
            dimensionsMap.put(DIMENSIONS_WORLDUUID, dimensions.getWorld().getUID().toString());
            dimensionsMap.put(DIMENSIONS_WORLDNAME, dimensions.getWorld().getName());
        }

        dimensionsMap.put(DIMENSIONS_XMIN, dimensions.getxMin());
        dimensionsMap.put(DIMENSIONS_YMIN, dimensions.getYMin());
        dimensionsMap.put(DIMENSIONS_ZMIN, dimensions.getZMin());
        dimensionsMap.put(DIMENSIONS_XMAX, dimensions.getXMax());
        dimensionsMap.put(DIMENSIONS_YMAX, dimensions.getYMax());
        dimensionsMap.put(DIMENSIONS_ZMAX, dimensions.getZMax());

        saveMap.put(DIMENSIONS, dimensionsMap);

        Map<String, Object> destinationMap = new HashMap<>();

        if(destination instanceof LocationDestination) {
            destinationMap.put(DESTINATION_TYPE, LocationDestination.class.getCanonicalName());
            if (dimensions.getWorld() == null) {
                destinationMap.put(DESTINATION_LOCATION_WORLDUUID, null);
                destinationMap.put(DESTINATION_LOCATION_WORLDNAME, null);
            } else {
                destinationMap.put(DESTINATION_LOCATION_WORLDUUID, ((LocationDestination) destination).getWorld().getUID().toString());
                destinationMap.put(DESTINATION_LOCATION_WORLDNAME, ((LocationDestination) destination).getWorld().getName());
            }

            destinationMap.put(DESTINATION_LOCATION_X, ((LocationDestination) destination).getX());
            destinationMap.put(DESTINATION_LOCATION_Y, ((LocationDestination) destination).getY());
            destinationMap.put(DESTINATION_LOCATION_Z, ((LocationDestination) destination).getZ());
            destinationMap.put(DESTINATION_LOCATION_PITCH, ((double) ((LocationDestination) destination).getPitch()));
            destinationMap.put(DESTINATION_LOCATION_YAW, ((double) ((LocationDestination) destination).getYaw()));
        } else if(destination instanceof PortalDestination) {
            destinationMap.put(DESTINATION_TYPE, PortalDestination.class.getCanonicalName());
            destinationMap.put(DESTINATION_PORTAL_PORTALID, ((PortalDestination) destination).getPortalId());
        }

        saveMap.put(DESTINATION, destinationMap);

        return saveMap;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        Map<String, Object> objectMap;
        if (section instanceof Map) {
            objectMap = (Map<String, Object>) section;
        } else {
            objectMap = (Map<String, Object>) ((ConfigSection) section).getRawMap();
        }

        Map<String, Object> dimensionsMap;
        if (section instanceof Map) {
            dimensionsMap = (Map<String, Object>) objectMap.get(DIMENSIONS);
        } else {
            dimensionsMap = (Map<String, Object>) ((ConfigSection) objectMap.get(DIMENSIONS)).getRawMap();
        }
        PortalDimensions dimensions = new PortalDimensions(
                Bukkit.getWorld(java.util.UUID.fromString((String) dimensionsMap.get(DIMENSIONS_WORLDUUID))),
                (Double) dimensionsMap.get(DIMENSIONS_XMIN),
                (Double) dimensionsMap.get(DIMENSIONS_YMIN),
                (Double) dimensionsMap.get(DIMENSIONS_ZMIN),
                (Double) dimensionsMap.get(DIMENSIONS_XMAX),
                (Double) dimensionsMap.get(DIMENSIONS_YMAX),
                (Double) dimensionsMap.get(DIMENSIONS_ZMAX)
        );
        Map<String, Object> destinationMap;
        if (section instanceof Map) {
            destinationMap = (Map<String, Object>) objectMap.get(DESTINATION);
        } else {
            destinationMap = (Map<String, Object>) ((ConfigSection) objectMap.get(DESTINATION)).getRawMap();
        }
        AbstractDestination destination = null;

        if(destinationMap == null || destinationMap.get(DESTINATION_TYPE) == null) {
            // DO NOTHING
        } else if(destinationMap.get(DESTINATION_TYPE).equals(LocationDestination.class.getCanonicalName())) {
            destination = new LocationDestination(
                    Bukkit.getWorld(java.util.UUID.fromString((String) destinationMap.get(DIMENSIONS_WORLDUUID))),
                    (Double) destinationMap.get(DESTINATION_LOCATION_X),
                    (Double) destinationMap.get(DESTINATION_LOCATION_Y),
                    (Double) destinationMap.get(DESTINATION_LOCATION_Z),
                    ((Double) destinationMap.get(DESTINATION_LOCATION_PITCH)).floatValue(),
                    ((Double) destinationMap.get(DESTINATION_LOCATION_YAW)).floatValue()
            );
        } else if(destinationMap.get(DESTINATION_TYPE).equals(PortalDestination.class.getCanonicalName())) {
            destination = new PortalDestination(
                    (String) destinationMap.get(DESTINATION_PORTAL_PORTALID)
            );
        }

        return new Portal(
                (String) objectMap.get(ID),
                dimensions,
                destination
        );
    }

    @Override
    public boolean supports(Class<?> type) {
        return Portal.class.isAssignableFrom(type);
    }
}
