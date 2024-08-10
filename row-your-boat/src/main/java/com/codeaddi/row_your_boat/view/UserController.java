package com.codeaddi.row_your_boat.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/")
    public String index(Model model) {
        return "login";
    }

}
