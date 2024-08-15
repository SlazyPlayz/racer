package bg.softuni.exam_retake_racer.model.dto.user;

import bg.softuni.exam_retake_racer.validation.annotation.FileNotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class UserRegisterBindingModel {

    @NotNull(message = "First name is required")
    @Size(min = 3, max = 24, message = "First name must be between 3 and 24 characters")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 3, max = 24, message = "Last name must be between 3 and 24 characters")
    private String lastName;

    @NotNull(message = "Username is required")
    @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters")
    private String username;

    @Email(message = "Email must be valid")
    @NotNull(message = "Email is required")
    @Size(min = 3, max = 48, message = "Email must be between 3 and 48 characters")
    private String email;

    @NotNull(message = "Bio is required")
    @Size(min = 3, max = 512, message = "Bio must be between 3 and 512 characters")
    private String bio;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 48, message = "Password must be between 4 and 48 characters")
    private String password;

    @NotNull(message = "Please confirm your password")
    @Size(min = 8, max = 48, message = "Password must be between 4 and 48 characters")
    private String confirmPassword;

    @FileNotNull(message = "Profile image is required")
    private MultipartFile image;

    public UserRegisterBindingModel() {
    }

    public @NotNull(message = "First name is required") @Size(min = 3, max = 24, message = "First name must be between 3 and 24 characters") String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(@NotNull(message = "First name is required") @Size(min = 3, max = 24, message = "First name must be between 3 and 24 characters") String firstName) {
        this.firstName = firstName;
        return this;
    }

    public @NotNull(message = "Last name is required") @Size(min = 3, max = 24, message = "Last name must be between 3 and 24 characters") String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(@NotNull(message = "Last name is required") @Size(min = 3, max = 24, message = "Last name must be between 3 and 24 characters") String lastName) {
        this.lastName = lastName;
        return this;
    }

    public @NotNull(message = "Username is required") @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters") String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(@NotNull(message = "Username is required") @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters") String username) {
        this.username = username;
        return this;
    }

    public @Email(message = "Email must be valid") @NotNull(message = "Email is required") @Size(min = 3, max = 48, message = "Email must be between 3 and 48 characters") String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(@Email(message = "Email must be valid") @NotNull(message = "Email is required") @Size(min = 3, max = 48, message = "Email must be between 3 and 48 characters") String email) {
        this.email = email;
        return this;
    }

    public @NotNull(message = "Bio is required") @Size(min = 3, max = 512, message = "Bio must be between 3 and 512 characters") String getBio() {
        return bio;
    }

    public UserRegisterBindingModel setBio(@NotNull(message = "Bio is required") @Size(min = 3, max = 512, message = "Bio must be between 3 and 512 characters") String bio) {
        this.bio = bio;
        return this;
    }

    public @NotNull(message = "Password is required") @Size(min = 8, max = 48, message = "Password must be between 4 and 48 characters") String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(@NotNull(message = "Password is required") @Size(min = 8, max = 48, message = "Password must be between 4 and 48 characters") String password) {
        this.password = password;
        return this;
    }

    public @NotNull(message = "Please confirm your password") @Size(min = 8, max = 48, message = "Password must be between 4 and 48 characters") String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(@NotNull(message = "Please confirm your password") @Size(min = 8, max = 48, message = "Password must be between 4 and 48 characters") String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public @FileNotNull(message = "Profile image is required") MultipartFile getImage() {
        return image;
    }

    public UserRegisterBindingModel setImage(@FileNotNull(message = "Profile image is required") MultipartFile image) {
        this.image = image;
        return this;
    }
}
