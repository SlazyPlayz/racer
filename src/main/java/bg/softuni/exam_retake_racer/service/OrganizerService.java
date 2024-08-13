package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.OrganizerDTO;

import java.util.Set;

public interface OrganizerService {

    void addOrganizer(OrganizerDTO organizerDTO);

    OrganizerDTO getOrganizerByName(String name);

    OrganizerDTO getOrganizerByIdentificationNumber(String identificationNumber);

    Set<OrganizerDTO> getAllOrganizers();
}
