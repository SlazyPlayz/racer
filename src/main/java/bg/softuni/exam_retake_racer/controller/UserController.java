package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.exceptions.InvalidInputException;
import bg.softuni.exam_retake_racer.exceptions.UserAlreadyExistsException;
import bg.softuni.exam_retake_racer.model.dto.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Logger logger;

    public UserController(UserService userService) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    // Register the user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(UserRegisterBindingModel userRegisterBindingModel) {
        try {
            UserDTO userDTO = userService.register(userRegisterBindingModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
        } catch (UserAlreadyExistsException e) {
            logger.error("User registration failed - user already exists", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (InvalidInputException e) {
            logger.error("User registration failed - invalid input", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request for invalid input
        }
    }

    //Catch additional exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        logger.error("An unexpected error occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}
