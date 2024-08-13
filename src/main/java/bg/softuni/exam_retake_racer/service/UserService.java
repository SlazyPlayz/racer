package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.user.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserRegisterBindingModel;

public interface UserService {

    UserDTO register(UserRegisterBindingModel userRegisterBindingModel);

    UserDTO getUser();
}
