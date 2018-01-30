package com.mapcat.mapcatsdk.testapp.model.annotations;

import com.mapcat.mapcatsdk.annotations.BaseMarkerViewOptions;
import com.mapcat.mapcatsdk.annotations.MarkerView;

public class TextMarkerView extends MarkerView {

  private String text;

  public TextMarkerView(BaseMarkerViewOptions baseMarkerViewOptions, String text) {
    super(baseMarkerViewOptions);
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
