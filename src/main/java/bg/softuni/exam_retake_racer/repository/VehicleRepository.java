package bg.softuni.exam_retake_racer.repository;

import bg.softuni.exam_retake_racer.model.entity.vehicle.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID> {

    Optional<VehicleEntity> findByMake(String make);

    Optional<VehicleEntity> findByMakeAndModel(String make, String model);
}
