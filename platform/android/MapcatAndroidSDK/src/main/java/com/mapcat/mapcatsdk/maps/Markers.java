package com.mapcat.mapcatsdk.maps;


import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mapcat.mapcatsdk.annotations.BaseMarkerOptions;
import com.mapcat.mapcatsdk.annotations.BaseMarkerViewOptions;
import com.mapcat.mapcatsdk.annotations.Marker;
import com.mapcat.mapcatsdk.annotations.MarkerView;
import com.mapcat.mapcatsdk.annotations.MarkerViewManager;

import java.util.List;

/**
 * Interface that defines convenient methods for working with a {@link Marker}'s collection.
 */
interface Markers {
  Marker addBy(@NonNull BaseMarkerOptions markerOptions, @NonNull MapboxMap mapboxMap);

  List<Marker> addBy(@NonNull List<? extends BaseMarkerOptions> markerOptionsList, @NonNull MapboxMap mapboxMap);

  void update(@NonNull Marker updatedMarker, @NonNull MapboxMap mapboxMap);

  List<Marker> obtainAll();

  List<Marker> obtainAllIn(@NonNull RectF rectangle);

  MarkerView addViewBy(@NonNull BaseMarkerViewOptions markerOptions, @NonNull MapboxMap mapboxMap,
                       @Nullable MarkerViewManager.OnMarkerViewAddedListener onMarkerViewAddedListener);

  List<MarkerView> addViewsBy(@NonNull List<? extends BaseMarkerViewOptions> markerViewOptions,
                              @NonNull MapboxMap mapboxMap);

  List<MarkerView> obtainViewsIn(@NonNull RectF rectangle);

  void reload();
}
