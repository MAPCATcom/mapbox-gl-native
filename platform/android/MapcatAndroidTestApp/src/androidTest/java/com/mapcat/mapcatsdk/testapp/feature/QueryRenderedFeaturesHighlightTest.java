package com.mapcat.mapcatsdk.testapp.feature;

import com.mapcat.mapcatsdk.testapp.R;
import com.mapcat.mapcatsdk.testapp.activity.BaseActivityTest;
import com.mapbox.mapboxsdk.testapp.activity.feature.QueryRenderedFeaturesBoxHighlightActivity;

import org.junit.Ignore;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Instrumentation test to validate if clicking box on screen highlights features.
 */
public class QueryRenderedFeaturesHighlightTest extends BaseActivityTest {

  @Override
  protected Class getActivityClass() {
    return QueryRenderedFeaturesBoxHighlightActivity.class;
  }

  @Test
  @Ignore
  public void testCountFeatures() {
    // click on box to query map
    onView(withId(R.id.selection_box)).perform(click());

    // validate if toast is shown
    onView(withText("50 features in box"))
      .inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView()))))
      .check(matches(isDisplayed()));
  }

}
