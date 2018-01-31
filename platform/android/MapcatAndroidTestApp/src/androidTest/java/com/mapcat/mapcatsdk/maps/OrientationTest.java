package com.mapcat.mapcatsdk.maps;

import com.mapcat.mapcatsdk.testapp.activity.BaseActivityTest;
import com.mapbox.mapboxsdk.testapp.activity.camera.CameraAnimationTypeActivity;
import com.mapcat.mapcatsdk.testapp.action.OrientationChangeAction;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

public class OrientationTest extends BaseActivityTest {

  @Test
  public void testChangeDeviceOrientation() {
    onView(isRoot()).perform(OrientationChangeAction.orientationLandscape());
    waitLoop(2200);
    onView(isRoot()).perform(OrientationChangeAction.orientationPortrait());
    waitLoop(2500);
    onView(isRoot()).perform(OrientationChangeAction.orientationLandscapeReverse());
    waitLoop(500);
    onView(isRoot()).perform(OrientationChangeAction.orientationPortraitReverse());
    waitLoop(1250);
    onView(isRoot()).perform(OrientationChangeAction.orientationLandscape());
    waitLoop(750);
    onView(isRoot()).perform(OrientationChangeAction.orientationPortrait());
    waitLoop(950);
    onView(isRoot()).perform(OrientationChangeAction.orientationLandscapeReverse());
    onView(isRoot()).perform(OrientationChangeAction.orientationPortraitReverse());
    onView(isRoot()).perform(OrientationChangeAction.orientationLandscape());
    onView(isRoot()).perform(OrientationChangeAction.orientationPortrait());
  }

  @Override
  protected Class getActivityClass() {
    return CameraAnimationTypeActivity.class;
  }

}
