package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.model.dto.UserLoginBindingModel;
import bg.softuni.exam_retake_racer.model.dto.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Logger logger;

    public UserController(UserService userService) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("userLoginBindingModel", UserLoginBindingModel.empty());
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("userRegisterBindingModel", UserRegisterBindingModel.empty());
        return modelAndView;
    }

    // Register the user
    @PostMapping("/register")
    public ModelAndView register(UserRegisterBindingModel userRegisterBindingModel) {
        userService.register(userRegisterBindingModel);

        return new ModelAndView("redirect:/users/login");
    }

    @GetMapping("/profile")
    public ModelAndView profile() throws Exception {
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", userService.getUser());
        return modelAndView;
    }

    //Catch additional exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        logger.error("An unexpected error occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}
