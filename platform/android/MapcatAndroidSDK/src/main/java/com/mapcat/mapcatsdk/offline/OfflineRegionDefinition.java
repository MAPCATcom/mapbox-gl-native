package com.mapcat.mapcatsdk.offline;

import com.mapcat.mapcatsdk.geometry.LatLngBounds;

/**
 * This is the interface that all Offline Region definitions have to implement.
 * <p>
 * For the present, a tile pyramid is the only type of offline region.
 */
public interface OfflineRegionDefinition {

  LatLngBounds getBounds();

}
