package ru.rshb.intech.digital.commons.utils;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Values {

  private final Map<String, Object> map;


  public Values() {
    this.map = new HashMap<>();
  }

  public Values(@Nonnull Map<String, Object> initValues) {
    this.map = new HashMap<>(initValues);
  }

  @Nonnull
  public static Values of(@Nonnull Map<String, Object> initValues) {
    return new Values(initValues);
  }


  @Nonnull
  public Values put(@Nonnull String key, @Nullable Object value) {
    this.map.put(key, value);
    return this;
  }

  @Nonnull
  public Values put(@Nonnull Object key, @Nullable Object value) {
    this.put(key.toString(), value);
    return this;
  }

  @Nullable
  public Object get(@Nonnull String key) {
    return map.get(key);
  }

  @Nonnull
  public Map<String, Object> asMap() {
    return Collections.unmodifiableMap(map);
  }

  public int size() {
    return map.size();
  }
}
