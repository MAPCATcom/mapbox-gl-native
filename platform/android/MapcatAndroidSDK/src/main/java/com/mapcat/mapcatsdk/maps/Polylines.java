package com.mapcat.mapcatsdk.maps;


import android.support.annotation.NonNull;

import com.mapcat.mapcatsdk.annotations.Polyline;
import com.mapcat.mapcatsdk.annotations.PolylineOptions;

import java.util.List;

/**
 * Interface that defines convenient methods for working with a {@link Polyline}'s collection.
 */
interface Polylines {
  Polyline addBy(@NonNull PolylineOptions polylineOptions, @NonNull MapboxMap mapboxMap);

  List<Polyline> addBy(@NonNull List<PolylineOptions> polylineOptionsList, @NonNull MapboxMap mapboxMap);

  void update(Polyline polyline);

  List<Polyline> obtainAll();
}
