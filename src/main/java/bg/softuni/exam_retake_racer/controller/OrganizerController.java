package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.OrganizerDTO;
import bg.softuni.exam_retake_racer.service.OrganizerService;
import org.springframework.stereotype.Controller;
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
        ModelAndView modelAndView = new ModelAndView("/races/organizer-info");
        modelAndView.addObject("organizer", organizerService.getOrganizerByName(name));
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("/races/add-organizer");
        modelAndView.addObject("organizerDTO", new OrganizerDTO());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView add(OrganizerDTO organizerDTO) {
        organizerService.addOrganizer(organizerDTO);

        return new ModelAndView("redirect:/");
    }
}
