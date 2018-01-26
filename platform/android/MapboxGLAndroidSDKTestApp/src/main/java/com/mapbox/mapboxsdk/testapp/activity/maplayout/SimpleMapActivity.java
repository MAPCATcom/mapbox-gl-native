package com.mapbox.mapboxsdk.testapp.activity.maplayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapcat;
import com.mapbox.mapboxsdk.maps.LayerOptions;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.testapp.R;
import com.mapbox.mapboxsdk.testapp.utils.TokenUtils;

/**
 * Test activity showcasing a simple MapView without any MapboxMap interaction.
 */
public class SimpleMapActivity extends AppCompatActivity {

  private MapView mapView;

  private String mapcatAccessToken;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_simple);

    mapView = (MapView) findViewById(R.id.mapView);
    mapView.onCreate(savedInstanceState);

    promptAccessToken();

    Button promptAccessTokenButton = (Button) findViewById(R.id.promptAccessToken);
    promptAccessTokenButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        promptAccessToken();
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

  private void promptAccessToken() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Your MAPCAT access token");
    final EditText input = new EditText(this);
    input.setInputType(InputType.TYPE_CLASS_TEXT);
    builder.setView(input);
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        mapcatAccessToken = input.getText().toString();
        initMap();
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // TODO: remove this button
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        mapcatAccessToken = TokenUtils.getMapcatAccessToken(getApplicationContext());
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
    Mapcat.setAccessToken(mapcatAccessToken);
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
