package com.mapcat.mapcatsdk.maps;

import android.util.Log;

import java.util.ArrayList;

public class MapViewInitHandler {
    static ArrayList<MapViewInitListener> listeners = new ArrayList<>();
    public static void registerListener(MapViewInitListener listener) {
        for (int i = 0; i < listeners.size(); ++i) {
            if (listeners.get(i) == listener) {
                return;
            }
        }
        listeners.add(listener);
    }
    public static void unregisterListener(MapViewInitListener listener) {
        for (int i = 0; i < listeners.size(); ++i) {
            if (listeners.get(i) == listener) {
                listeners.remove(listener);
                return;
            }
        }
    }
    public static void onError(String error) {
        for (int i = 0; i < listeners.size(); ++i) {
            listeners.get(i).onMapViewInitError(error);
        }
    }
    public static void onSuccess() {
        for (int i = 0; i < listeners.size(); ++i) {
            listeners.get(i).onMapViewInitSuccess();
        }
    }
}
