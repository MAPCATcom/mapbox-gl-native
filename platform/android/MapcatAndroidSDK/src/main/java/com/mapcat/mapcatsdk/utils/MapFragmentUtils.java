package com.mapcat.mapcatsdk.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.mapcat.mapcatsdk.constants.MapboxConstants;
import com.mapcat.mapcatsdk.maps.MapboxMapOptions;
import com.mapcat.mapcatsdk.maps.MapFragment;
import com.mapcat.mapcatsdk.maps.SupportMapFragment;

/**
 * MapFragment utility class.
 * <p>
 * Used to extract duplicate code between {@link MapFragment} and
 * {@link SupportMapFragment}.
 * </p>
 */
public class MapFragmentUtils {

  /**
   * Convert MapboxMapOptions to a bundle of fragment arguments.
   *
   * @param options The MapboxMapOptions to convert
   * @return a bundle of converted fragment arguments
   */
  public static Bundle createFragmentArgs(MapboxMapOptions options) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(MapboxConstants.FRAG_ARG_MAPBOXMAPOPTIONS, options);
    return bundle;
  }

  /**
   * Convert a bundle of fragment arguments to MapboxMapOptions.
   *
   * @param context The context of the activity hosting the fragment
   * @param args    The fragment arguments
   * @return converted MapboxMapOptions
   */
  public static MapboxMapOptions resolveArgs(Context context, Bundle args) {
    MapboxMapOptions options;
    if (args != null && args.containsKey(MapboxConstants.FRAG_ARG_MAPBOXMAPOPTIONS)) {
      options = args.getParcelable(MapboxConstants.FRAG_ARG_MAPBOXMAPOPTIONS);
    } else {
      // load default options
      options = MapboxMapOptions.createFromAttributes(context, null);
    }
    options = loadDefaultMyLocationViewDrawables(context, options);
    return options;
  }

  private static MapboxMapOptions loadDefaultMyLocationViewDrawables(Context context, MapboxMapOptions options) {
    Drawable foregroundDrawable = options.getMyLocationForegroundDrawable();
    Drawable foregroundBearingDrawable = options.getMyLocationForegroundBearingDrawable();
    if (foregroundDrawable == null || foregroundBearingDrawable == null) {
      if (foregroundDrawable == null) {
        foregroundDrawable = ContextCompat.getDrawable(context, com.mapcat.mapcatsdk.R.drawable.mapbox_mylocation_icon_default);
      }
      if (foregroundBearingDrawable == null) {
        foregroundBearingDrawable = ContextCompat.getDrawable(context, com.mapcat.mapcatsdk.R.drawable.mapbox_mylocation_icon_bearing);
      }
      options.myLocationForegroundDrawables(foregroundDrawable, foregroundBearingDrawable);
    }
    if (options.getMyLocationBackgroundDrawable() == null) {
      options.myLocationBackgroundDrawable(ContextCompat.getDrawable(context, com.mapcat.mapcatsdk.R.drawable.mapbox_mylocation_bg_shape));
    }
    return options;
  }
}
