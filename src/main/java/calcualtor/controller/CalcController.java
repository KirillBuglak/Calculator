package calcualtor.controller;

import calcualtor.service.CalcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalcController {
    private final CalcService service;

    public CalcController(CalcService service) {
        this.service = service;
    }

    @GetMapping
    public String index() {
        return "calc";
    }

    @PostMapping(value = "/", params = "get")
    public String add(@ModelAttribute("string") String string, Model model) {
        model.addAttribute("result", service.calculate(string));
        return "calc";
    }
}
