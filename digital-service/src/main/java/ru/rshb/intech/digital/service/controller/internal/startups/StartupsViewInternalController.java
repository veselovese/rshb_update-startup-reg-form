package ru.rshb.intech.digital.service.controller.internal.startups;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;

@Slf4j
@Hidden
@Controller
@RequestMapping("/internal/web/startups")
@Profile({"dev-local", "dev-stand"})
public class StartupsViewInternalController {


  @GetMapping(
      path = "/convert",
      produces = "text/html;charset=utf-8"
  )
  public ResponseEntity<String> getImportPage() {
    return ResponseEntity.ok(
        pageFromStaticContent("startups-parser.html")
    );
  }

  @Nonnull
  private String pageFromStaticContent(@Nonnull String resName) {
    try {
      return IOUtils.resourceToString("/static/" + resName, StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.error("Content reading error: " + resName, e);
      return StringUtils.EMPTY;
    }
  }
}
