package ru.rshb.intech.digital.service.controller.publications;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rshb.intech.digital.service.controller.publications.infographics.InfographicsController;
import ru.rshb.intech.digital.service.controller.publications.innovations.InnovationPublicationController;
import ru.rshb.intech.digital.service.controller.publications.podcasts.PodcastController;

@RestControllerAdvice(
    assignableTypes = {
        PublicationController.class,
        InfographicsController.class,
        InnovationPublicationController.class,
        PodcastController.class
    }
)
@Order(1)
public class PublicationExceptionHandler {

}
