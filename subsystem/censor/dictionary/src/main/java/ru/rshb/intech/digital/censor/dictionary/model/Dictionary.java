package ru.rshb.intech.digital.censor.dictionary.model;

import lombok.Getter;

import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Dictionary {

  private final List<Word> words;

  public Dictionary(@Nonnull List<Word> words) {
    this.words = new ArrayList<>(words);
  }

}
