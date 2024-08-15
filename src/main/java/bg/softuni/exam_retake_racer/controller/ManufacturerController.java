package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer.ManufacturerAddBindingModel;
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
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;
    private final VehicleService vehicleService;

    public ManufacturerController(ManufacturerService manufacturerService, VehicleService vehicleService) {
        this.manufacturerService = manufacturerService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/add")
    public ModelAndView addManufacturer() {
        ModelAndView modelAndView = new ModelAndView("/vehicles/add-manufacturer");
        modelAndView.addObject("manufacturer", new ManufacturerAddBindingModel());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addManufacturerConfirm(@Valid ManufacturerAddBindingModel manufacturerAddBindingModel,
                                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/vehicles/add-manufacturer");
            modelAndView.addObject("manufacturer", manufacturerAddBindingModel);
            return modelAndView;
        }

        manufacturerService.addManufacturer(manufacturerAddBindingModel);
        return new ModelAndView("redirect:/vehicles");
    }

    @GetMapping("/{name}")
    public ModelAndView manufacturerInfo(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("/vehicles/manufacturer-info");
        modelAndView.addObject("manufacturer", manufacturerService.getManufacturerBySearchName(name));
        modelAndView.addObject("vehicles", vehicleService.getVehicleByMake(name));
        return modelAndView;
    }

    @GetMapping("/{name}/vehicles")
    public ModelAndView getVehicles(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("/vehicles/vehicle-info");
        modelAndView.addObject("vehicles", vehicleService.getVehicleByMake(name));
        return modelAndView;
    }
}
