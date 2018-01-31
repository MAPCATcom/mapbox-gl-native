package com.mapcat.mapcatsdk.exceptions;

import android.content.Context;

import com.mapcat.mapcatsdk.Mapbox;

/**
 * A MapboxConfigurationException is thrown by MapboxMap when the SDK hasn't been properly initialised.
 * <p>
 * This occurs either when {@link Mapbox} is not correctly initialised or the provided access token
 * through {@link Mapbox#getInstance(Context, String)} isn't valid.
 * </p>
 *
 * @see Mapbox#getInstance(Context, String)
 */
public class MapboxConfigurationException extends RuntimeException {

  /**
   * Creates a Mapbox configuration exception thrown by MapboxMap when the SDK hasn't been properly initialised.
   */
  public MapboxConfigurationException() {
    super("\nUsing MapView requires setting a valid access token. Use Mapbox.getInstance(Context context, "
      + "String accessToken) to provide one. "
      + "\nPlease see https://www.mapbox.com/help/create-api-access-token/ to learn how to create one."
      + "\nMore information in this guide https://www.mapbox.com/help/first-steps-android-sdk/#access-tokens.");
  }
}
