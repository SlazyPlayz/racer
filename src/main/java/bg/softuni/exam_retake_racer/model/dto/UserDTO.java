package bg.softuni.exam_retake_racer.model.dto;

import java.util.Objects;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String bio;
    private String imageUrl;

    public UserDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public UserDTO setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
