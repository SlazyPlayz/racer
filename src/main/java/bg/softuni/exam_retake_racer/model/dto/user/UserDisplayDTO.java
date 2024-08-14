package bg.softuni.exam_retake_racer.model.dto.user;

public class UserDisplayDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String imageUrl;

    public String getFirstName() {
        return firstName;
    }

    public UserDisplayDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDisplayDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDisplayDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserDisplayDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
