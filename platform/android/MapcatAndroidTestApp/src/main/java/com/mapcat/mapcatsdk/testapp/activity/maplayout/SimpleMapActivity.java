package com.mapcat.mapcatsdk.testapp.activity.maplayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mapcat.mapcatsdk.Mapcat;
import com.mapcat.mapcatsdk.annotations.MarkerOptions;
import com.mapcat.mapcatsdk.camera.CameraPosition;
import com.mapcat.mapcatsdk.camera.CameraUpdateFactory;
import com.mapcat.mapcatsdk.geometry.LatLng;
import com.mapcat.mapcatsdk.maps.LayerOptions;
import com.mapcat.mapcatsdk.maps.MapView;
import com.mapcat.mapcatsdk.maps.MapViewInitHandler;
import com.mapcat.mapcatsdk.maps.MapViewInitListener;
import com.mapcat.mapcatsdk.maps.MapboxMap;
import com.mapcat.mapcatsdk.maps.OnMapReadyCallback;
import com.mapcat.mapcatsdk.testapp.R;
import com.mapcat.mapcatsdk.testapp.utils.TokenUtils;

/**
 * Test activity showcasing a simple MapView without any MapboxMap interaction.
 */
public class SimpleMapActivity extends AppCompatActivity implements MapViewInitListener {

  private MapView mapView;

  private String mapcatVisualizationApiKey;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_simple);

    mapView = (MapView) findViewById(R.id.mapView);
    mapView.onCreate(savedInstanceState);

    MapViewInitHandler.registerListener(this);

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
    MapViewInitHandler.unregisterListener(this);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override
  public void onMapViewInitError(final String error) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(getApplicationContext(), "Can't initialize Mapcat mapview with the given Visualization API key. Error: " + error, Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onMapViewInitSuccess() {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        mapView.getMapAsync(new OnMapReadyCallback() {
          @Override
          public void onMapReady(MapboxMap mapboxMap) {
            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(11.586863170412954,48.13930284413624))
                    .title("Cucc")
                    .snippet("Illinois")
            );
            CameraPosition position = new CameraPosition.Builder()
                    .target(new LatLng(47.111, 19.569))
                    .zoom(5)
                    .build();
            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 7000);
          }
        });
      }
    });
  }
}
