package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.user.ParticipantDTO;

public interface ParticipantService {

    void addParticipant(ParticipantDTO participantDTO);

    void removeParticipant(ParticipantDTO participantDTO);

    ParticipantDTO getParticipantByUsernameAndVehicle(ParticipantDTO participantDTO);
}
