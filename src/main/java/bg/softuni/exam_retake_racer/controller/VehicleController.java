package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.vehicle.VehicleDTO;
import bg.softuni.exam_retake_racer.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ModelAndView allVehicles() {
        ModelAndView modelAndView = new ModelAndView("vehicles/vehicles");
        modelAndView.addObject("vehicles", vehicleService.getAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addVehicle() {
        ModelAndView modelAndView = new ModelAndView("/vehicles/add-vehicle");
        modelAndView.addObject("vehicle", new VehicleDTO());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addVehicleConfirm(VehicleDTO vehicleDTO) {
        vehicleService.addVehicle(vehicleDTO);
        return new ModelAndView("redirect:/vehicles");
    }

    @GetMapping("/{make}/{model}")
    public ModelAndView getByMakeAndModel(@PathVariable String make, @PathVariable String model) {
        ModelAndView modelAndView = new ModelAndView("/vehicles/vehicle-info");
        modelAndView.addObject("vehicle", vehicleService.getVehicleByMakeAndModel(make, model));
        modelAndView.addObject("makeLogoUrl", vehicleService.getMakeLogoUrl(make));
        return modelAndView;
    }
}
