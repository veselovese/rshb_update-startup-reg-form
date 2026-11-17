package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordParticipantRepository;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrosswordParticipantServiceImpl implements CrosswordParticipantService {

  private final CrosswordParticipantRepository crosswordParticipantRepository;


  @Nonnull
  @Override
  @Transactional
  public CrosswordParticipant save(@Nonnull CrosswordParticipant participant) {
    participant.setSubmitDate(LocalDateTime.now());
    final var savedParticipant = crosswordParticipantRepository.save(participant);
    log.info("New crossword participant has been saved");
    return savedParticipant;
  }
}
