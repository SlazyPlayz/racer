package bg.softuni.exam_retake_racer.repository;

import bg.softuni.exam_retake_racer.model.entity.user.ParticipantEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.model.entity.vehicle.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, UUID> {

    Optional<ParticipantEntity> findByUserAndVehicle(UserEntity user, VehicleEntity vehicle);

    Set<ParticipantEntity> findByUser(UserEntity user);
}
