package bg.softuni.exam_retake_racer.model.dto.user;

import org.springframework.web.multipart.MultipartFile;

public class UserRegisterBindingModel {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String bio;
    private String password;
    private String confirmPassword;
    private MultipartFile image;

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public UserRegisterBindingModel setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public UserRegisterBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
