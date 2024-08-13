package bg.softuni.exam_retake_racer.repository;

import bg.softuni.exam_retake_racer.model.entity.race.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizerRepository extends JpaRepository<OrganizerEntity, UUID> {

    Optional<OrganizerEntity> findOrganizerByIdentificationNumber(String identificationNumber);

    Optional<OrganizerEntity> findOrganizerByName(String name);
}
