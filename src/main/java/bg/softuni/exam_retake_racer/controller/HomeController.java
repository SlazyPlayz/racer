package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.service.RaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class HomeController {

    private final RaceService raceService;

    public HomeController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("races", raceService.getAllRaces());
        return modelAndView;
    }
}
