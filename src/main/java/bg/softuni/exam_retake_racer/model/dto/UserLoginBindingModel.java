package bg.softuni.exam_retake_racer.model.dto;

public record UserLoginBindingModel(String username, String password) {
    public static UserLoginBindingModel empty() {
        return new UserLoginBindingModel("","");
    }
}
