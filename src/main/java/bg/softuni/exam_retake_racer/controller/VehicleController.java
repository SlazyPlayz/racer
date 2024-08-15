package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.vehicle.VehicleDTO;
import bg.softuni.exam_retake_racer.service.ManufacturerService;
import bg.softuni.exam_retake_racer.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final ManufacturerService manufacturerService;

    public VehicleController(VehicleService vehicleService, ManufacturerService manufacturerService) {
        this.vehicleService = vehicleService;
        this.manufacturerService = manufacturerService;
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
        modelAndView.addObject("manufacturers", manufacturerService.getAllManufacturers());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addVehicle(@Valid VehicleDTO vehicleDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/vehicles/add-vehicle");
            modelAndView.addObject("vehicle", vehicleDTO);
            return modelAndView;
        }

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

    @GetMapping("/{model}")
    public ModelAndView getBydModel(@PathVariable String model) {
        ModelAndView modelAndView = new ModelAndView("/vehicles/vehicle-info");
        modelAndView.addObject("vehicle", vehicleService.getVehicleBySearchName(model));
        return modelAndView;
    }
}
