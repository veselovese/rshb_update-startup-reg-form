package ru.rshb.intech.digital.puzzles.core.crossword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipantCounter;

public interface CrosswordParticipantCounterRepository extends JpaRepository<CrosswordParticipantCounter, String> {
}
