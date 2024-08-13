package bg.softuni.exam_retake_racer.repository;

import bg.softuni.exam_retake_racer.model.entity.race.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, UUID> {

    Optional<RaceEntity> findByName(String name);

    Optional<RaceEntity> findByOrganizerName(String organizerName);
}
