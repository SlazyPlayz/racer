package bg.softuni.exam_retake_racer.repository;

import bg.softuni.exam_retake_racer.model.entity.race.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrackRepository extends JpaRepository<TrackEntity, UUID> {

    Optional<TrackEntity> findTrackBySearchName(String name);

}
