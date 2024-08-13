package bg.softuni.exam_retake_racer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class HomeController {

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
