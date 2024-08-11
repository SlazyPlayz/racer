package bg.softuni.exam_retake_racer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeController {

    // TODO: Change T of return type
    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hi");
    }
}
