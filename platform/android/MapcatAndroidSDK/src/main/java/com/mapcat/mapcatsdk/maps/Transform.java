package com.mapcat.mapcatsdk.maps;

import android.graphics.PointF;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.mapcat.mapcatsdk.annotations.MarkerViewManager;
import com.mapcat.mapcatsdk.camera.CameraPosition;
import com.mapcat.mapcatsdk.camera.CameraUpdate;
import com.mapcat.mapcatsdk.camera.CameraUpdateFactory;
import com.mapcat.mapcatsdk.constants.MapboxConstants;
import com.mapcat.mapcatsdk.geometry.LatLng;
import com.mapcat.mapcatsdk.maps.widgets.MyLocationView;

import timber.log.Timber;

import static com.mapcat.mapcatsdk.maps.MapboxMap.OnCameraMoveStartedListener;

/**
 * Resembles the current Map transformation.
 * <p>
 * Responsible for synchronising {@link CameraPosition} state and notifying
 * {@link MapboxMap.OnCameraChangeListener}.
 * </p>
 */
final class Transform implements MapView.OnMapChangedListener {

  private final NativeMapView mapView;
  private final MarkerViewManager markerViewManager;
  private final TrackingSettings trackingSettings;
  private final MyLocationView myLocationView;
  private final Handler handler = new Handler();

  private CameraPosition cameraPosition;
  private MapboxMap.CancelableCallback cameraCancelableCallback;

  private MapboxMap.OnCameraChangeListener onCameraChangeListener;

  private CameraChangeDispatcher cameraChangeDispatcher;

  Transform(NativeMapView mapView, MarkerViewManager markerViewManager, TrackingSettings trackingSettings,
            CameraChangeDispatcher cameraChangeDispatcher) {
    this.mapView = mapView;
    this.markerViewManager = markerViewManager;
    this.trackingSettings = trackingSettings;
    this.myLocationView = trackingSettings.getMyLocationView();
    this.cameraChangeDispatcher = cameraChangeDispatcher;
  }

  void initialise(@NonNull MapboxMap mapboxMap, @NonNull MapboxMapOptions options) {
    CameraPosition position = options.getCamera();
    if (position != null && !position.equals(CameraPosition.DEFAULT)) {
      moveCamera(mapboxMap, CameraUpdateFactory.newCameraPosition(position), null);
    }
    setMinZoom(options.getMinZoomPreference());
    setMaxZoom(options.getMaxZoomPreference());
  }

  //
  // Camera API
  //

  @UiThread
  public final CameraPosition getCameraPosition() {
    if (cameraPosition == null) {
      cameraPosition = invalidateCameraPosition();
    }
    return cameraPosition;
  }

  @UiThread
  void updateCameraPosition(@NonNull CameraPosition position) {
    if (myLocationView != null) {
      myLocationView.setCameraPosition(position);
    }
    markerViewManager.setTilt((float) position.tilt);
  }

  @Override
  public void onMapChanged(@MapView.MapChange int change) {
    if (change == MapView.REGION_DID_CHANGE_ANIMATED) {
      updateCameraPosition(invalidateCameraPosition());
      if (cameraCancelableCallback != null) {
        handler.post(new Runnable() {
          @Override
          public void run() {
            if (cameraCancelableCallback != null) {
              cameraCancelableCallback.onFinish();
              cameraCancelableCallback = null;
            }
          }
        });
      }
      cameraChangeDispatcher.onCameraIdle();
      mapView.removeOnMapChangedListener(this);
    }
  }

  @UiThread
  final void moveCamera(MapboxMap mapboxMap, CameraUpdate update, final MapboxMap.CancelableCallback callback) {
    CameraPosition cameraPosition = update.getCameraPosition(mapboxMap);
    if (isValidCameraPosition(cameraPosition)) {
      trackingSettings.resetTrackingModesIfRequired(this.cameraPosition, cameraPosition, false);
      cancelTransitions();
      cameraChangeDispatcher.onCameraMoveStarted(OnCameraMoveStartedListener.REASON_API_ANIMATION);
      mapView.jumpTo(cameraPosition.bearing, cameraPosition.target, cameraPosition.tilt, cameraPosition.zoom);
      cameraChangeDispatcher.onCameraIdle();
      invalidateCameraPosition();
      handler.post(new Runnable() {
        @Override
        public void run() {
          if (callback != null) {
            callback.onFinish();
          }
        }
      });
    }
  }

  @UiThread
  final void easeCamera(MapboxMap mapboxMap, CameraUpdate update, int durationMs, boolean easingInterpolator,
                        final MapboxMap.CancelableCallback callback, boolean isDismissable) {
    CameraPosition cameraPosition = update.getCameraPosition(mapboxMap);
    if (isValidCameraPosition(cameraPosition)) {
      trackingSettings.resetTrackingModesIfRequired(this.cameraPosition, cameraPosition, isDismissable);
      cancelTransitions();
      cameraChangeDispatcher.onCameraMoveStarted(OnCameraMoveStartedListener.REASON_API_ANIMATION);

      if (callback != null) {
        cameraCancelableCallback = callback;
      }
      mapView.addOnMapChangedListener(this);
      mapView.easeTo(cameraPosition.bearing, cameraPosition.target, durationMs, cameraPosition.tilt,
        cameraPosition.zoom, easingInterpolator);
    }
  }

  @UiThread
  final void animateCamera(MapboxMap mapboxMap, CameraUpdate update, int durationMs,
                           final MapboxMap.CancelableCallback callback) {
    CameraPosition cameraPosition = update.getCameraPosition(mapboxMap);
    if (isValidCameraPosition(cameraPosition)) {
      trackingSettings.resetTrackingModesIfRequired(this.cameraPosition, cameraPosition, false);
      cancelTransitions();
      cameraChangeDispatcher.onCameraMoveStarted(OnCameraMoveStartedListener.REASON_API_ANIMATION);

      if (callback != null) {
        cameraCancelableCallback = callback;
      }
      mapView.addOnMapChangedListener(this);
      mapView.flyTo(cameraPosition.bearing, cameraPosition.target, durationMs, cameraPosition.tilt,
        cameraPosition.zoom);
    }
  }

  private boolean isValidCameraPosition(@Nullable CameraPosition cameraPosition) {
    return cameraPosition != null && !cameraPosition.equals(this.cameraPosition);
  }

  @UiThread
  @Nullable
  CameraPosition invalidateCameraPosition() {
    if (mapView != null) {
      CameraPosition cameraPosition = mapView.getCameraPosition();
      if (this.cameraPosition != null && !this.cameraPosition.equals(cameraPosition)) {
        cameraChangeDispatcher.onCameraMove();
      }

      if (isComponentUpdateRequired(cameraPosition)) {
        updateCameraPosition(cameraPosition);
      }

      this.cameraPosition = cameraPosition;
      if (onCameraChangeListener != null) {
        onCameraChangeListener.onCameraChange(this.cameraPosition);
      }
    }
    return cameraPosition;
  }

  private boolean isComponentUpdateRequired(@NonNull CameraPosition cameraPosition) {
    return this.cameraPosition != null && (this.cameraPosition.tilt != cameraPosition.tilt
      || this.cameraPosition.bearing != cameraPosition.bearing);
  }

  void cancelTransitions() {
    // notify user about cancel
    cameraChangeDispatcher.onCameraMoveCanceled();

    // notify animateCamera and easeCamera about cancelling
    if (cameraCancelableCallback != null) {
      final MapboxMap.CancelableCallback callback = cameraCancelableCallback;
      cameraChangeDispatcher.onCameraIdle();
      handler.post(new Runnable() {
        @Override
        public void run() {
          callback.onCancel();
        }
      });
      cameraCancelableCallback = null;
    }

    // cancel ongoing transitions
    mapView.cancelTransitions();
  }

  @UiThread
  void resetNorth() {
    cancelTransitions();
    mapView.resetNorth();
  }

  //
  // Camera change listener API
  //

  void setOnCameraChangeListener(@Nullable MapboxMap.OnCameraChangeListener listener) {
    this.onCameraChangeListener = listener;
  }

  //
  // non Camera API
  //

  // Zoom in or out

  double getZoom() {
    return cameraPosition.zoom;
  }

  double getRawZoom() {
    return mapView.getZoom();
  }

  void zoom(boolean zoomIn, @NonNull PointF focalPoint) {
    CameraPosition cameraPosition = invalidateCameraPosition();
    if (cameraPosition != null) {
      int newZoom = (int) Math.round(cameraPosition.zoom + (zoomIn ? 1 : -1));
      setZoom(newZoom, focalPoint, MapboxConstants.ANIMATION_DURATION, false);
    } else {
      // we are not transforming, notify about being idle
      cameraChangeDispatcher.onCameraIdle();
    }
  }

  void zoom(double zoomAddition, @NonNull PointF focalPoint, long duration) {
    CameraPosition cameraPosition = invalidateCameraPosition();
    if (cameraPosition != null) {
      int newZoom = (int) Math.round(cameraPosition.zoom + zoomAddition);
      setZoom(newZoom, focalPoint, duration, false);
    } else {
      // we are not transforming, notify about being idle
      cameraChangeDispatcher.onCameraIdle();
    }
  }

  void setZoom(double zoom, @NonNull PointF focalPoint) {
    setZoom(zoom, focalPoint, 0, false);
  }

  void setZoom(double zoom, @NonNull PointF focalPoint, long duration, boolean isAnimator) {
    if (mapView != null) {
      mapView.addOnMapChangedListener(new MapView.OnMapChangedListener() {
        @Override
        public void onMapChanged(int change) {
          if (change == MapView.REGION_DID_CHANGE_ANIMATED) {
            if (!isAnimator) {
              cameraChangeDispatcher.onCameraIdle();
            }
            mapView.removeOnMapChangedListener(this);
          }
        }
      });
      mapView.setZoom(zoom, focalPoint, duration);
    }
  }

  // Direction
  double getBearing() {
    double direction = -mapView.getBearing();

    while (direction > 360) {
      direction -= 360;
    }
    while (direction < 0) {
      direction += 360;
    }

    return direction;
  }

  double getRawBearing() {
    return mapView.getBearing();
  }

  void setBearing(double bearing) {
    if (myLocationView != null) {
      myLocationView.setBearing(bearing);
    }
    mapView.setBearing(bearing);
  }

  void setBearing(double bearing, float focalX, float focalY) {
    if (myLocationView != null) {
      myLocationView.setBearing(bearing);
    }
    mapView.setBearing(bearing, focalX, focalY);
  }

  void setBearing(double bearing, float focalX, float focalY, long duration) {
    if (myLocationView != null) {
      myLocationView.setBearing(bearing);
    }
    mapView.setBearing(bearing, focalX, focalY, duration);
  }


  //
  // LatLng / CenterCoordinate
  //

  LatLng getLatLng() {
    return mapView.getLatLng();
  }

  //
  // Pitch / Tilt
  //

  double getTilt() {
    return mapView.getPitch();
  }

  void setTilt(Double pitch) {
    if (myLocationView != null) {
      myLocationView.setTilt(pitch);
    }
    markerViewManager.setTilt(pitch.floatValue());
    mapView.setPitch(pitch, 0);
  }

  //
  // Center coordinate
  //

  LatLng getCenterCoordinate() {
    return mapView.getLatLng();
  }

  void setCenterCoordinate(LatLng centerCoordinate) {
    mapView.setLatLng(centerCoordinate);
  }

  void setGestureInProgress(boolean gestureInProgress) {
    mapView.setGestureInProgress(gestureInProgress);
    if (!gestureInProgress) {
      invalidateCameraPosition();
    }
  }

  void zoomBy(double z, float x, float y) {
    mapView.setZoom(mapView.getZoom() + z, new PointF(x, y), 0);
  }

  void moveBy(double offsetX, double offsetY, long duration) {
    if (duration > 0) {
      mapView.addOnMapChangedListener(new MapView.OnMapChangedListener() {
        @Override
        public void onMapChanged(int change) {
          if (change == MapView.REGION_DID_CHANGE_ANIMATED) {
            mapView.removeOnMapChangedListener(this);
            cameraChangeDispatcher.onCameraIdle();
          }
        }
      });
    }
    mapView.moveBy(offsetX, offsetY, duration);
  }

  //
  // Min & Max ZoomLevel
  //

  void setMinZoom(double minZoom) {
    if ((minZoom < MapboxConstants.MINIMUM_ZOOM) || (minZoom > MapboxConstants.MAXIMUM_ZOOM)) {
      Timber.e("Not setting minZoomPreference, value is in unsupported range: %s", minZoom);
      return;
    }
    mapView.setMinZoom(minZoom);
  }

  double getMinZoom() {
    return mapView.getMinZoom();
  }

  void setMaxZoom(double maxZoom) {
    if ((maxZoom < MapboxConstants.MINIMUM_ZOOM) || (maxZoom > MapboxConstants.MAXIMUM_ZOOM)) {
      Timber.e("Not setting maxZoomPreference, value is in unsupported range: %s", maxZoom);
      return;
    }
    mapView.setMaxZoom(maxZoom);
  }

  double getMaxZoom() {
    return mapView.getMaxZoom();
  }
}