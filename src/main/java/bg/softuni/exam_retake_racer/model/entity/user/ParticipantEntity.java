package bg.softuni.exam_retake_racer.model.entity.user;

import bg.softuni.exam_retake_racer.model.entity.BaseEntity;
import bg.softuni.exam_retake_racer.model.entity.vehicle.VehicleEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "participants")
public class ParticipantEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "vehicle_uuid")
    private VehicleEntity vehicle;

    public UserEntity getUser() {
        return user;
    }

    public ParticipantEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public ParticipantEntity setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
        return this;
    }
}
