package com.codeaddi.row_your_boat.view.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ResourcesController {

  @GetMapping("/boats")
  public String boats() {
    return "resources/boats";
  }

  @GetMapping("/blades")
  public String blades() {
    return "resources/blades";
  }
}
