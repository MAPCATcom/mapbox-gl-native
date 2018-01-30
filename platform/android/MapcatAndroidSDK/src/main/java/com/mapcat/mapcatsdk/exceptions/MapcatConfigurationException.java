package com.mapcat.mapcatsdk.exceptions;

import android.content.Context;

import com.mapcat.mapcatsdk.Mapcat;

/**
 * A MapcatConfigurationException is thrown by MapboxMap when the SDK hasn't been properly initialised.
 * <p>
 * This occurs either when {@link Mapcat} is not correctly initialised or the provided access token
 * through {@link Mapcat#getInstance(Context, String)} isn't valid.
 * </p>
 *
 * @see Mapcat#getInstance(Context, String)
 */
public class MapcatConfigurationException extends RuntimeException {

  /**
   * Creates a Mapcat configuration exception thrown by MapboxMap when the SDK hasn't been properly initialised.
   */
  public MapcatConfigurationException() {
    super("\nUsing MapView requires setting a valid access token. Use Mapcat.getInstance(Context context, "
      + "String accessToken) to provide one. "
      + "\nPlease see https://docs.mapcat.com/#get-started-with-mapcat to learn how to create one.");
  }
}
