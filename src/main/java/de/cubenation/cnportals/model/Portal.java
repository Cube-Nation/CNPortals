package de.cubenation.cnportals.model;

import de.cubenation.cnportals.model.destination.AbstractDestination;

public class Portal implements Comparable {

    private String id;
    private PortalDimensions dimensions;
    private AbstractDestination destination;

    public Portal(String id, PortalDimensions dimensions) {
        this.id = id;
        this.dimensions = dimensions;
    }

    public Portal(String id, PortalDimensions dimensions, AbstractDestination destination) {
        this.id = id;
        this.dimensions = dimensions;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PortalDimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(PortalDimensions dimensions) {
        this.dimensions = dimensions;
    }

    public AbstractDestination getDestination() {
        return destination;
    }

    public void setDestination(AbstractDestination destination) {
        this.destination = destination;
    }

    public boolean hasValidDestination() {
        return destination != null && destination.getLocation(null) != null;
    }

    @Override
    public int compareTo(Object o) {
        Portal other = (Portal) o;
        return this.getId().compareTo(other.getId());
    }
}
