package bg.softuni.exam_retake_racer.model.dto.user.participant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ParticipantAddBindingModel {

    @NotNull(message = "Vehicle make is required")
    @Size(min = 3, max = 20, message = "Make length must be between 3 and 20 characters")
    private String vehicleMake;

    @NotNull(message = "Vehicle model is required")
    @Size(min = 3, max = 60, message = "Model length must be between 3 and 60 characters")
    private String vehicleModel;

    public ParticipantAddBindingModel() {
    }

    public @NotNull(message = "Vehicle make is required") @Size(min = 3, max = 20, message = "Make length must be between 3 and 20 characters") String getVehicleMake() {
        return vehicleMake;
    }

    public ParticipantAddBindingModel setVehicleMake(@NotNull(message = "Vehicle make is required") @Size(min = 3, max = 20, message = "Make length must be between 3 and 20 characters") String vehicleMake) {
        this.vehicleMake = vehicleMake;
        return this;
    }

    public @NotNull(message = "Vehicle model is required") @Size(min = 3, max = 60, message = "Model length must be between 3 and 60 characters") String getVehicleModel() {
        return vehicleModel;
    }

    public ParticipantAddBindingModel setVehicleModel(@NotNull(message = "Vehicle model is required") @Size(min = 3, max = 60, message = "Model length must be between 3 and 60 characters") String vehicleModel) {
        this.vehicleModel = vehicleModel;
        return this;
    }
}
