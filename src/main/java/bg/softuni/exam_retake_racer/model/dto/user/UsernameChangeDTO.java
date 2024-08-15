package bg.softuni.exam_retake_racer.model.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsernameChangeDTO {

    @NotNull(message = "Username is required")
    @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters")
    private String newUsername;

    public UsernameChangeDTO() {
    }

    public @NotNull(message = "Username is required") @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters") String getNewUsername() {
        return newUsername;
    }

    public UsernameChangeDTO setNewUsername(@NotNull(message = "Username is required") @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters") String newUsername) {
        this.newUsername = newUsername;
        return this;
    }
}
