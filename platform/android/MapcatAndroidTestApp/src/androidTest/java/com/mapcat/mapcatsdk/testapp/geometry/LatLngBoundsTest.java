package com.mapcat.mapcatsdk.testapp.geometry;

import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapcat.mapcatsdk.testapp.action.MapboxMapAction;
import com.mapcat.mapcatsdk.testapp.activity.BaseActivityTest;
import com.mapbox.mapboxsdk.testapp.activity.feature.QueryRenderedFeaturesBoxHighlightActivity;

import org.junit.Test;

/**
 * Instrumentation test to validate integration of LatLngBounds
 */
public class LatLngBoundsTest extends BaseActivityTest {

  @Override
  protected Class getActivityClass() {
    return QueryRenderedFeaturesBoxHighlightActivity.class;
  }

  @Test
  public void testLatLngBounds() {
    // regression test for #9322
    validateTestSetup();
    MapboxMapAction.invoke(mapboxMap, (uiController, mapboxMap) -> {
      LatLngBounds bounds = new LatLngBounds.Builder()
        .include(new LatLng(48.8589506, 2.2773457))
        .include(new LatLng(47.2383171, -1.6309316))
        .build();
      mapboxMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
    });
  }
}