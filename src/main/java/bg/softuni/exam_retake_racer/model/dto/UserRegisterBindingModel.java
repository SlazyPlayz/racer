package bg.softuni.exam_retake_racer.model.dto;

public record UserRegisterBindingModel(String firstName, String lastName, String username, String email, String bio, String password, String confirmPassword) {
    public static UserRegisterBindingModel empty() {
        return new UserRegisterBindingModel("", "", "", "", "", "", "");
    }
}
