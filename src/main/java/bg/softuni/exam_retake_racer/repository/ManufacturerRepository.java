package bg.softuni.exam_retake_racer.repository;

import bg.softuni.exam_retake_racer.model.entity.vehicle.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerEntity, UUID> {

    Optional<ManufacturerEntity> findManufacturerEntityByName(String name);
}
