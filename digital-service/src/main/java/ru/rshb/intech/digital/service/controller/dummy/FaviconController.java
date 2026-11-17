package ru.rshb.intech.digital.service.controller.dummy;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Hidden
@Controller
public class FaviconController {

  @GetMapping("favicon.ico")
  @ResponseBody
  public void returnNoFavicon() {
  }
}
