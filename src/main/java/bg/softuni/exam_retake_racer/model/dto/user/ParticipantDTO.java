package bg.softuni.exam_retake_racer.model.dto.user;

public class ParticipantDTO {

    private String username;
    private String vehicleMake;
    private String vehicleModel;

    public String getUsername() {
        return username;
    }

    public ParticipantDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public ParticipantDTO setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
        return this;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public ParticipantDTO setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
        return this;
    }
}
