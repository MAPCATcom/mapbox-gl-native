package com.mapcat.mapcatsdk.maps;

import android.graphics.RectF;

import com.mapcat.mapcatsdk.annotations.Annotation;

import java.util.List;

interface ShapeAnnotations {

  List<Annotation> obtainAllIn(RectF rectF);

}
