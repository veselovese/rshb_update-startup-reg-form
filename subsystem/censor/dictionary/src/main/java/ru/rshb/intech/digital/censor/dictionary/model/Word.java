package ru.rshb.intech.digital.censor.dictionary.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"text"})
@NoArgsConstructor
@AllArgsConstructor
public class Word {

  private String text;

}
