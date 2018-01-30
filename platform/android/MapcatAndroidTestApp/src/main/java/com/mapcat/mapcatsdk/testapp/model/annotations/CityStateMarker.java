package com.mapcat.mapcatsdk.testapp.model.annotations;

import com.mapcat.mapcatsdk.annotations.Marker;

public class CityStateMarker extends Marker {

  private String infoWindowBackgroundColor;

  public CityStateMarker(CityStateMarkerOptions cityStateOptions, String color) {
    super(cityStateOptions);
    infoWindowBackgroundColor = color;
  }

  public String getInfoWindowBackgroundColor() {
    return infoWindowBackgroundColor;
  }

}
