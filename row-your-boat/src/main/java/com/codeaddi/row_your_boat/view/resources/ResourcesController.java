package com.codeaddi.row_your_boat.view.resources;

import com.codeaddi.row_your_boat.controller.services.view.ViewService;
import com.codeaddi.row_your_boat.model.http.inbound.Boat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class ResourcesController {
  @Autowired
  ViewService viewService;

  @GetMapping("/boats")
  public String boats(Model model) {
    List<Boat> boats = viewService.getAllBoats();
    model.addAttribute("boats", boats);
    return "resources/boats";
  }

  @GetMapping("/blades")
  public String blades() {
    return "resources/blades";
  }
}
