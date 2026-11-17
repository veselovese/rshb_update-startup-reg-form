package ru.rshb.intech.digital.publications.core.repository.views;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.publications.entity.PostViewHistoryItem;
import ru.rshb.intech.digital.publications.entity.PostViewHistoryItemId;

public interface PostViewHistoryRepository extends JpaRepository<PostViewHistoryItem, PostViewHistoryItemId> {


}
