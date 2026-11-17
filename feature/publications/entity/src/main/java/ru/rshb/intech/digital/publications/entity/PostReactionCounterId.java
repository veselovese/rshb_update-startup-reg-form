package ru.rshb.intech.digital.publications.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.publications.dto.LikeState;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReactionCounterId implements Serializable {

    private String publicationId;

    private LikeState reaction;
}
