package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.user.UsernameChangeDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserLoginBindingModel;
import bg.softuni.exam_retake_racer.model.dto.user.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.service.RaceService;
import bg.softuni.exam_retake_racer.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RaceService raceService;
    private final Logger logger;

    public UserController(UserService userService, RaceService raceService) {
        this.userService = userService;
        this.raceService = raceService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("/users/login");
        modelAndView.addObject("userLoginBindingModel", new UserLoginBindingModel());
        return modelAndView;
    }

    @PostMapping("/login-error")
    public ModelAndView loginError(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/users/login");
            modelAndView.addObject("userLoginBindingModel", userLoginBindingModel);
            return modelAndView;
        }

        return new ModelAndView("index");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("/users/register");
        modelAndView.addObject("userRegisterBindingModel", new UserRegisterBindingModel());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/users/register");
            modelAndView.addObject("userRegisterBindingModel", userRegisterBindingModel);
            return modelAndView;
        }

        userService.register(userRegisterBindingModel);
        return new ModelAndView("redirect:/users/login");
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("/users/profile");
        modelAndView.addObject("user", userService.getUser());
        modelAndView.addObject("changeUser", new UsernameChangeDTO());
        modelAndView.addObject("races", raceService.getUserRaces());
        return modelAndView;
    }

    @PatchMapping("/username")
    public ModelAndView changeUsername(@Valid UsernameChangeDTO usernameChangeDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/users/profile");
            modelAndView.addObject("changeUser", usernameChangeDTO);
            return modelAndView;
        }

        userService.changeUsername(usernameChangeDTO);
        return new ModelAndView("redirect:/users/profile");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        logger.error("An unexpected error occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}
