package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerDTO;

import java.util.Set;

public interface OrganizerService {

    void addOrganizer(OrganizerAddBindingModel organizerAddBindingModel);

    OrganizerDTO getOrganizerByName(String name);

    OrganizerDTO getOrganizerByIdentificationNumber(String identificationNumber);

    Set<OrganizerDTO> getAllOrganizers();

    Set<String> getAllOrganizerNames();
}
