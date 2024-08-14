package bg.softuni.exam_retake_racer.model.dto.user;

public class UsernameChangeDTO {

    private String newUsername;

    public UsernameChangeDTO() {
    }

    public String getNewUsername() {
        return newUsername;
    }

    public UsernameChangeDTO setNewUsername(String newUsername) {
        this.newUsername = newUsername;
        return this;
    }
}
