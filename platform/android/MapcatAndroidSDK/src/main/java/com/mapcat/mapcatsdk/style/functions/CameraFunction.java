package com.mapcat.mapcatsdk.style.functions;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;

import com.mapcat.mapcatsdk.style.functions.stops.ExponentialStops;
import com.mapcat.mapcatsdk.style.functions.stops.IntervalStops;
import com.mapcat.mapcatsdk.style.functions.stops.Stop;
import com.mapcat.mapcatsdk.style.functions.stops.Stops;

/**
 * Camera function. Functions that take camera properties as input (zoom for now)
 * <p>
 * Zoom functions allow the appearance of a map feature to change with map’s zoom level.
 * Zoom functions can be used to create the illusion of depth and control data density.
 * Each stop is an array with two elements: the first is a zoom level and the second is
 * a function output value.
 *
 * @param <I> the input type
 * @param <O> the output type
 * @see Function#zoom
 */
public class CameraFunction<I extends Number, O> extends Function<I, O> {

  /**
   * Create an exponential camera function
   *
   * @param stops @see {@link Stops#exponential(float, Stop[])}
   */
  CameraFunction(@NonNull ExponentialStops<I, O> stops) {
    super(stops);
  }

  /**
   * Create an interval camera function
   *
   * @param stops @see {@link Stops#interval(Stop[])}
   */
  CameraFunction(@NonNull IntervalStops<I, O> stops) {
    super(stops);
  }

  /**
   * JNI constructor
   */
  @Keep
  private CameraFunction(Stops<I, O> stops) {
    super(stops);
  }
}
