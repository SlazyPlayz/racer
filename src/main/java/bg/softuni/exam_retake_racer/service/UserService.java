package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.race.DisplayRaceDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.model.dto.user.UsernameChangeDTO;

import java.util.Set;

public interface UserService {

    UserDTO register(UserRegisterBindingModel userRegisterBindingModel);

    UserDTO getUser();

    void changeUsername(UsernameChangeDTO usernameChangeDTO);

    Set<DisplayRaceDTO> getRaces();

    Set<String> getPhotoIds();
}
