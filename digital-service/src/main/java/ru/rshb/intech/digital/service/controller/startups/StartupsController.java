package ru.rshb.intech.digital.service.controller.startups;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@Tag(
    name = "Стартапы",
    description = "API стартапов."
)
@RestController
@RequestMapping(
    path = "${api.prefix}/startups",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class StartupsController {

}
