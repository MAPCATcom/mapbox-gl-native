package com.mapcat.mapcatsdk.testapp.utils;


import android.content.Context;
import android.support.annotation.NonNull;

import com.mapcat.mapcatsdk.Mapcat;

public class TokenUtils {

  public TokenUtils() {
    super();
  }

  /**
   * <p>
   * Returns the Mapcat Visualization API key set in the app resources.
   * </p>
   * It will first search for an API key in the Mapcat object. If not found it
   * will then attempt to load the Visualization API key from the
   * {@code res/values/dev.xml} development file.
   *
   * @param context The {@link Context} of the {@link android.app.Activity} or {@link android.app.Fragment}.
   * @return The Mapcat Visualization API key or null if not found.
   */
  public static String getMapcatVisualizationApiKey(@NonNull Context context) {
    try {
      // Read out AndroidManifest
      String key = Mapcat.getVisualizationApiKey();
      if (key == null || key.isEmpty()) {
        throw new IllegalArgumentException();
      }
      return key;
    } catch (Exception exception) {
      // Use fallback on string resource, used for development
      int keyResId = context.getResources()
        .getIdentifier("mapcat_visualization_api_key", "string", context.getPackageName());
      return keyResId != 0 ? context.getString(keyResId) : null;
    }
  }
}
