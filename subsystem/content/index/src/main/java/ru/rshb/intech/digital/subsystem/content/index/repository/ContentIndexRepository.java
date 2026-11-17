package ru.rshb.intech.digital.subsystem.content.index.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.subsystem.content.index.entity.ContentIndexEntry;

public interface ContentIndexRepository extends JpaRepository<ContentIndexEntry, String> {

}
