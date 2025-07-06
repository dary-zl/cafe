package com.example.cafe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @GetMapping()
    public String menuPage() {
        return "menu";
    }

    @GetMapping("/{category}")
    public String menuCategoryPage(@PathVariable String category) {
        return "menu/" + "error";
    }
}
