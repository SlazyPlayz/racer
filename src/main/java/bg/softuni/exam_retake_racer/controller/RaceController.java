package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.RaceDTO;
import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerDTO;
import bg.softuni.exam_retake_racer.model.dto.user.participant.ParticipantAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.user.participant.ParticipantDTO;
import bg.softuni.exam_retake_racer.service.OrganizerService;
import bg.softuni.exam_retake_racer.service.ParticipantService;
import bg.softuni.exam_retake_racer.service.RaceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RestController
@RequestMapping("/races")
public class RaceController {

    private final RaceService raceService;
    private final OrganizerService organizerService;
    private final ParticipantService participantService;

    public RaceController(RaceService raceService, OrganizerService organizerService, ParticipantService participantService) {
        this.raceService = raceService;
        this.organizerService = organizerService;
        this.participantService = participantService;
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
        Set<OrganizerDTO> organizers = organizerService.getAllOrganizers();
        modelAndView.addObject("raceAddBindingModel", new RaceAddBindingModel());
        modelAndView.addObject("organizers", organizers);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid RaceAddBindingModel raceAddBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/races/add-race");
            Set<String> organizers = organizerService.getAllOrganizerNames();
            modelAndView.addObject("raceAddBindingModel", raceAddBindingModel);
            modelAndView.addObject("organizers", organizers);
            return modelAndView;
        }

        raceService.addRace(raceAddBindingModel);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/{name}")
    public ModelAndView details(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("/races/race-info");
        modelAndView.addObject("race", raceService.getRaceByName(name));
        return modelAndView;
    }

    @GetMapping("/upcoming")
    public ResponseEntity<Set<RaceDTO>> upcomingRaces() {
        return ResponseEntity.ok(raceService.getUpcomingRaces());
    }

    @GetMapping("/participate/{name}")
    public ModelAndView participate(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("/races/participate");
        modelAndView.addObject("raceName", name);
        modelAndView.addObject("participantAddBindingModel", new ParticipantAddBindingModel());
        return modelAndView;
    }

    @PostMapping("/participate/{name}")
    public ModelAndView participate(@PathVariable String name, @Valid ParticipantAddBindingModel participantAddBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/races/participate");
            modelAndView.addObject("raceName", name);
            modelAndView.addObject("participantAddBindingModel", participantAddBindingModel);
            return modelAndView;
        }

        participantService.addParticipant(participantAddBindingModel);
        raceService.addParticipant(name, participantAddBindingModel);

        return new ModelAndView("redirect:/");
    }
}
