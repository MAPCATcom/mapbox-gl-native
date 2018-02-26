package com.mapcat.mapcatsdk.testapp.activity.maplayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mapcat.mapcatsdk.Mapcat;
import com.mapcat.mapcatsdk.maps.LayerOptions;
import com.mapcat.mapcatsdk.maps.MapView;
import com.mapcat.mapcatsdk.testapp.R;
import com.mapcat.mapcatsdk.testapp.utils.TokenUtils;

/**
 * Test activity showcasing a simple MapView without any MapboxMap interaction.
 */
public class SimpleMapActivity extends AppCompatActivity {

  private MapView mapView;

  private String mapcatVisualizationApiKey;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_simple);

    mapView = (MapView) findViewById(R.id.mapView);
    mapView.onCreate(savedInstanceState);

    promptVisualizationApiKey();

    Button promptVisualizationApiKeyButton = (Button) findViewById(R.id.promptVisualizationApiKey);
    promptVisualizationApiKeyButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        promptVisualizationApiKey();
      }
    });

    Button promptLanguageCodeButton = (Button) findViewById(R.id.promptLanguageCode);
    promptLanguageCodeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        promptLanguage();
      }
    });
  }

  private void promptVisualizationApiKey() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Your MAPCAT Visualization API key");
    final EditText input = new EditText(this);
    input.setInputType(InputType.TYPE_CLASS_TEXT);
    builder.setView(input);
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        mapcatVisualizationApiKey = input.getText().toString();
        initMap();
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // TODO: remove this button
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        mapcatVisualizationApiKey = TokenUtils.getMapcatVisualizationApiKey(getApplicationContext());
        initMap();
      }
    });
    builder.setCancelable(false);
    builder.show();
  }

  private void promptLanguage() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Set language code");
    final EditText input = new EditText(this);
    input.setInputType(InputType.TYPE_CLASS_TEXT);
    builder.setView(input);
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        String languageCode = input.getText().toString();
        mapView.setLanguage(languageCode);
      }
    });
    builder.show();
  }

  private void initMap() {
    Mapcat.setVisualizationApiKey(mapcatVisualizationApiKey);
    mapView.initMapcatMap(new LayerOptions(true, true));
  }

  @Override
  protected void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mapView.onStop();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }
}
