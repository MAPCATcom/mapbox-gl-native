package com.mapcat.mapcatsdk.testapp.model.annotations;

import com.mapcat.mapcatsdk.annotations.BaseMarkerOptions;
import com.mapcat.mapcatsdk.annotations.Marker;

public class CountryMarker extends Marker {

  private String abbrevName;
  private int flagRes;

  public CountryMarker(BaseMarkerOptions baseMarkerOptions, String abbrevName, int iconRes) {
    super(baseMarkerOptions);
    this.abbrevName = abbrevName;
    this.flagRes = iconRes;
  }

  public String getAbbrevName() {
    return abbrevName;
  }

  public int getFlagRes() {
    return flagRes;
  }
}
