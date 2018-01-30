package com.mapcat.mapcatsdk.maps;


import android.support.annotation.NonNull;

import com.mapcat.mapcatsdk.annotations.Polygon;
import com.mapcat.mapcatsdk.annotations.PolygonOptions;

import java.util.List;

/**
 * Interface that defines convenient methods for working with a {@link Polygon}'s collection.
 */
interface Polygons {
  Polygon addBy(@NonNull PolygonOptions polygonOptions, @NonNull MapboxMap mapboxMap);

  List<Polygon> addBy(@NonNull List<PolygonOptions> polygonOptionsList, @NonNull MapboxMap mapboxMap);

  void update(Polygon polygon);

  List<Polygon> obtainAll();
}
