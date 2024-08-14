package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.race.track.TrackAddBindingModel;
import bg.softuni.exam_retake_racer.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public ModelAndView getTracks() {
        ModelAndView modelAndView = new ModelAndView("/races/tracks");
        modelAndView.addObject("tracks", trackService.getAllTracks());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addTrack(){
        ModelAndView modelAndView = new ModelAndView("/races/add-track");
        modelAndView.addObject("trackAddBindingModel", new TrackAddBindingModel());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addTrack(TrackAddBindingModel trackAddBindingModel) {
        trackService.addTrack(trackAddBindingModel);
        return new ModelAndView("redirect:/tracks");
    }
}
