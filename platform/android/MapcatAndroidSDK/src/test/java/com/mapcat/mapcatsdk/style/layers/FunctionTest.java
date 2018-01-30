package com.mapcat.mapcatsdk.style.layers;

import com.mapcat.mapcatsdk.style.functions.Function;
import com.mapcat.mapcatsdk.style.functions.stops.Stops;

import org.junit.Test;

import static com.mapcat.mapcatsdk.style.functions.Function.zoom;
import static com.mapcat.mapcatsdk.style.functions.stops.Stop.stop;
import static com.mapcat.mapcatsdk.style.layers.PropertyFactory.lineBlur;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests Function
 */
public class FunctionTest {

  @Test
  public void testZoomFunction() {
    Function<Float, Float> zoomF = zoom(Stops.interval(
      stop(1f, lineBlur(1f)),
      stop(10f, lineBlur(20f))
      )
    );

    assertNotNull(zoomF.toValueObject());
    assertArrayEquals(
      new Object[] {new Object[] {1f, 1f}, new Object[] {10f, 20f}},
      (Object[]) zoomF.toValueObject().get("stops")
    );
  }

}
