package ru.rshb.intech.digital.puzzles.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordGuessResult {

    private String crosswordId;
    private Boolean complete;
    private List<String> wrong;
}
