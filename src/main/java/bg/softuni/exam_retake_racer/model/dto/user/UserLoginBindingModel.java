package bg.softuni.exam_retake_racer.model.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginBindingModel {

    @NotNull(message = "Username is required")
    @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 48, message = "Password must be between 4 and 48 characters")
    private String password;

    private boolean rememberMe;

    public UserLoginBindingModel() {}

    public @NotNull(message = "Username is required") @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters") String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(@NotNull(message = "Username is required") @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters") String username) {
        this.username = username;
        return this;
    }

    public @NotNull(message = "Password is required") @Size(min = 8, max = 48, message = "Password must be between 4 and 48 characters") String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(@NotNull(message = "Password is required") @Size(min = 8, max = 48, message = "Password must be between 4 and 48 characters") String password) {
        this.password = password;
        return this;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public UserLoginBindingModel setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
        return this;
    }
}
