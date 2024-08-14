package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.service.OrganizerService;
import bg.softuni.exam_retake_racer.service.RaceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RestController
@RequestMapping("/races")
public class RaceController {

    private final RaceService raceService;
    private final OrganizerService organizerService;

    public RaceController(RaceService raceService, OrganizerService organizerService) {
        this.raceService = raceService;
        this.organizerService = organizerService;
    }

    @GetMapping
    public ModelAndView all() {
        ModelAndView modelAndView = new ModelAndView("/races/races");
        modelAndView.addObject("races", raceService.getAllRaces());
        return modelAndView;
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
        raceService.addRace(raceAddBindingModel);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/{name}")
    public ModelAndView details(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("/races/race-info");
        modelAndView.addObject("race", raceService.getRaceByName(name));
        return modelAndView;
    }
}
