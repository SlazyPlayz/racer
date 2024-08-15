package bg.softuni.exam_retake_racer.model.dto.user;

public record UserLoginBindingModel(String username, String password, boolean rememberMe) {
    public static UserLoginBindingModel empty() {
        return new UserLoginBindingModel("","", false);
    }
}
