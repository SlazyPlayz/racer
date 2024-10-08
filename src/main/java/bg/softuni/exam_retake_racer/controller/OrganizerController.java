package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerAddBindingModel;
import bg.softuni.exam_retake_racer.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping("/{name}")
    public ModelAndView info(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("/organizers/organizer-info");
        modelAndView.addObject("organizer", organizerService.getOrganizerBySearchName(name));
        modelAndView.addObject("races", organizerService.getRacesByOrganizerName(name));
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("/organizers/add-organizer");
        modelAndView.addObject("organizerAddBindingModel", new OrganizerAddBindingModel());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid OrganizerAddBindingModel organizerAddBindingModel, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/organizers/add-organizer");
            modelAndView.addObject("organizerAddBindingModel", new OrganizerAddBindingModel());
            return modelAndView;
        }

        organizerService.addOrganizer(organizerAddBindingModel);
        return new ModelAndView("redirect:/");
    }
}
