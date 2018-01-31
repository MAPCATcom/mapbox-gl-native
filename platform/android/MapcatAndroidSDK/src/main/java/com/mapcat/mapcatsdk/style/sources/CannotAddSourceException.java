package com.mapcat.mapcatsdk.style.sources;

/**
 * Thrown when adding a source to a map twice
 */
public class CannotAddSourceException extends RuntimeException {

  public CannotAddSourceException(String message) {
    super(message);
  }

}
