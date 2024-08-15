package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.user.participant.ParticipantAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.user.participant.ParticipantDTO;

public interface ParticipantService {

    void addParticipant(ParticipantAddBindingModel participantAddBindingModel);

    void removeParticipant(ParticipantDTO participantDTO);

    ParticipantDTO getParticipantByUsernameAndVehicle(ParticipantDTO participantDTO);
}
