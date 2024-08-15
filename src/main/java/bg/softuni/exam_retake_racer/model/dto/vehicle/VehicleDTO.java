package bg.softuni.exam_retake_racer.model.dto.vehicle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VehicleDTO {

    @NotNull(message = "Vehicle make is required")
    @Size(min = 3, max = 20, message = "Make length must be between 3 and 20 characters")
    private String make;

    @NotNull(message = "Vehicle model is required")
    @Size(min = 3, max = 60, message = "Model length must be between 3 and 60 characters")
    private String model;

    @NotNull(message = "Vehicle year is required")
    @Min(value = 0, message = "Year must be a positive number")
    private Integer year;

    @NotNull(message = "Vehicle horsepower is required")
    @Min(value = 0, message = "Horsepower must be a positive number")
    private Integer horsepower;

    public VehicleDTO() {
    }

    public @NotNull(message = "Vehicle make is required") @Size(min = 3, max = 20, message = "Make length must be between 3 and 20 characters") String getMake() {
        return make;
    }

    public VehicleDTO setMake(@NotNull(message = "Vehicle make is required") @Size(min = 3, max = 20, message = "Make length must be between 3 and 20 characters") String make) {
        this.make = make;
        return this;
    }

    public @NotNull(message = "Vehicle model is required") @Size(min = 3, max = 60, message = "Model length must be between 3 and 60 characters") String getModel() {
        return model;
    }

    public VehicleDTO setModel(@NotNull(message = "Vehicle model is required") @Size(min = 3, max = 60, message = "Model length must be between 3 and 60 characters") String model) {
        this.model = model;
        return this;
    }

    public @NotNull(message = "Vehicle year is required") @Min(value = 0, message = "Year must be a positive number") Integer getYear() {
        return year;
    }

    public VehicleDTO setYear(@NotNull(message = "Vehicle year is required") @Min(value = 0, message = "Year must be a positive number") Integer year) {
        this.year = year;
        return this;
    }

    public @NotNull(message = "Vehicle horsepower is required") @Min(value = 0, message = "Horsepower must be a positive number") Integer getHorsepower() {
        return horsepower;
    }

    public VehicleDTO setHorsepower(@NotNull(message = "Vehicle horsepower is required") @Min(value = 0, message = "Horsepower must be a positive number") Integer horsepower) {
        this.horsepower = horsepower;
        return this;
    }
}
