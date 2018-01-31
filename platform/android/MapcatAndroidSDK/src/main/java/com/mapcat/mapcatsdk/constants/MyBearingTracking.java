package com.mapcat.mapcatsdk.constants;

import android.support.annotation.IntDef;

import com.mapcat.mapcatsdk.maps.MapboxMap;
import com.mapcat.mapcatsdk.maps.widgets.MyLocationView;
import com.mapcat.mapcatsdk.maps.TrackingSettings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * MyBearingTracking exposes different types of bearing tracking modes.
 * <p>
 * These modes visualise the user direction by extracting the direction from either sensor or location data.
 * </p>
 * <p>
 * Required to enable showing the user location first through {@link MapboxMap#setMyLocationEnabled(boolean)}.
 * </p>
 *
 * @see TrackingSettings#setMyBearingTrackingMode(int)
 * @see MyLocationView#setMyBearingTrackingMode(int)
 */
public class MyBearingTracking {

  @IntDef( {NONE, COMPASS, GPS})
  @Retention(RetentionPolicy.SOURCE)
  public @interface Mode {
  }

  /**
   * Bearing tracking is disabled
   */
  public static final int NONE = 0x00000000;

  /**
   * Tracking the bearing of the user based on sensor data
   */
  public static final int COMPASS = 0x00000004;

  /**
   * Tracking the bearing of the user based on GPS data
   */
  public static final int GPS = 0x00000008;

}
