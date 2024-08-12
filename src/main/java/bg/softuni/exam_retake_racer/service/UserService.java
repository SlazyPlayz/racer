package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.UserRegisterBindingModel;

public interface UserService {

    UserDTO register(UserRegisterBindingModel userRegisterBindingModel);

    UserDTO getUser();
}
