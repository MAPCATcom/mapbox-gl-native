package com.mapcat.mapcatsdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.text.TextUtils;

import com.mapcat.mapcatsdk.exceptions.MapcatConfigurationException;
import com.mapcat.mapcatsdk.location.LocationSource;
import com.mapcat.mapcatsdk.net.ConnectivityReceiver;
import com.mapbox.services.android.telemetry.location.LocationEngine;
import com.mapbox.services.android.telemetry.location.LocationEnginePriority;
import com.mapbox.services.android.telemetry.location.LocationEngineProvider;

/**
 * The entry point to initialize the Mapcat Android SDK.
 * <p>
 * Obtain a reference by calling {@link #getInstance(Context, String)}. Usually this class is configured in
 * Application#onCreate() and is responsible for the active Visualization API key, application context, and
 * connectivity state.
 * </p>
 */
@UiThread
public final class Mapcat {

  @SuppressLint("StaticFieldLeak")
  private static Mapcat INSTANCE;
  private Context context;
  private String visualizationApiKey;
  private Boolean connected;
  private LocationEngine locationEngine;

  /**
   * Get an instance of Mapcat.
   * <p>
   * This class manages the active Visualization API key, application context, and connectivity state.
   * </p>
   *
   * @param context     Android context which holds or is an application context
   * @param visualizationApiKey Mapcat Visualization API key
   * @return the single instance of Mapcat
   */
  @UiThread
  public static synchronized Mapcat getInstance(@NonNull Context context, @NonNull String visualizationApiKey) {
    if (INSTANCE == null) {
      Context appContext = context.getApplicationContext();
      LocationEngineProvider locationEngineProvider = new LocationEngineProvider(context);
      LocationEngine locationEngine = locationEngineProvider.obtainBestLocationEngineAvailable();
      INSTANCE = new Mapcat(appContext, visualizationApiKey, locationEngine);
      locationEngine.setPriority(LocationEnginePriority.NO_POWER);

      ConnectivityReceiver.instance(appContext);
    }
    return INSTANCE;
  }

  Mapcat(@NonNull Context context, @NonNull String visualizationApiKey, LocationEngine locationEngine) {
    this.context = context;
    this.visualizationApiKey = visualizationApiKey;
    this.locationEngine = locationEngine;
  }

  /**
   * Visualization API key for this application.
   *
   * @return Mapcat Visualization API key
   */
  public static String getVisualizationApiKey() {
    validateMapcat();
    return INSTANCE.visualizationApiKey;
  }

  /**
   * Set Visualization API key for this application
   * @param value the Visualization API key to set
   */
  public static void setVisualizationApiKey(String value) {
    validateMapcat();
    INSTANCE.visualizationApiKey = value;
  }

  /**
   * Runtime validation of Mapcat creation.
   */
  private static void validateMapcat() throws MapcatConfigurationException {
    if (INSTANCE == null) {
      throw new MapcatConfigurationException();
    }
  }

  /**
   * Runtime validation of Visualization API key.
   *
   * @throws MapcatConfigurationException exception thrown when not using a valid visualizationApiKey
   */
  private static void validateVisualizationApiKey() throws MapcatConfigurationException {
    String key = INSTANCE.visualizationApiKey;
    if (TextUtils.isEmpty(key)) {
      throw new MapcatConfigurationException();
    }
  }

  /**
   * Application context
   *
   * @return the application context
   */
  public static Context getApplicationContext() {
    return INSTANCE.context;
  }

  /**
   * Manually sets the connectivity state of the app. This is useful for apps which control their
   * own connectivity state and want to bypass any checks to the ConnectivityManager.
   *
   * @param connected flag to determine the connectivity state, true for connected, false for
   *                  disconnected, and null for ConnectivityManager to determine.
   */
  public static synchronized void setConnected(Boolean connected) {
    // Connectivity state overridden by app
    INSTANCE.connected = connected;
  }

  /**
   * Determines whether we have an internet connection available. Please do not rely on this
   * method in your apps. This method is used internally by the SDK.
   *
   * @return true if there is an internet connection, false otherwise
   */
  public static synchronized Boolean isConnected() {
    if (INSTANCE.connected != null) {
      // Connectivity state overridden by app
      return INSTANCE.connected;
    }

    ConnectivityManager cm = (ConnectivityManager) INSTANCE.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return (activeNetwork != null && activeNetwork.isConnected());
  }

  /**
   * Returns a location source instance with empty methods.
   *
   * @return an empty location source implementation
   * @deprecated Replaced by {@link Mapcat#getLocationEngine()}
   */
  @Deprecated
  public static LocationSource getLocationSource() {
    return new EmptyLocationSource();
  }


  /**
   * Returns the location engine used by the SDK.
   *
   * @return the location engine configured
   */
  public static LocationEngine getLocationEngine() {
    return INSTANCE.locationEngine;
  }
}
