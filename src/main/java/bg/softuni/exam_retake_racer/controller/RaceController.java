package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.service.OrganizerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RestController
@RequestMapping("/races")
public class RaceController {

    private final OrganizerService organizerService;

    public RaceController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("/races/add-race");
        Set<String> organizers = organizerService.getAllOrganizerNames();
        modelAndView.addObject("raceAddBindingModel", new RaceAddBindingModel());
        modelAndView.addObject("organizers", organizers);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView add(RaceAddBindingModel raceAddBindingModel) {
        raceSer
        return new ModelAndView("redirect:/");
    }
}
