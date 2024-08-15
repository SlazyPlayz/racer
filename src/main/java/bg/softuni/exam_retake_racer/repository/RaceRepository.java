package bg.softuni.exam_retake_racer.repository;

import bg.softuni.exam_retake_racer.model.entity.race.RaceEntity;
import bg.softuni.exam_retake_racer.model.entity.user.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, UUID> {

    Optional<RaceEntity> findBySearchName(String name);

    Optional<RaceEntity> findByOrganizerName(String organizerName);

    Set<RaceEntity> findAllByParticipantsContaining(Set<ParticipantEntity> participants);

    Set<RaceEntity> findAllByDateAfter(Date date);
}
