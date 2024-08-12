package bg.softuni.exam_retake_racer.model.entity.vehicle;

import bg.softuni.exam_retake_racer.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "vehicles")
public class VehicleEntity extends BaseEntity {

    @Column(name = "make", unique = true)
    @NotNull(message = "Vehicle make is required")
    @Size(min = 3, max = 20, message = "Make length must be between 3 and 20 characters")
    private String make;

    @Column(name = "model", unique = true)
    @NotNull(message = "Vehicle model is required")
    @Size(min = 3, max = 60, message = "Model length must be between 3 and 60 characters")
    private String model;

    @Column(name = "year")
    @NotNull(message = "Vehicle year is required")
    @Min(value = 0, message = "Year must be a positive number")
    private Integer year;

    @Column(name = "horsepower")
    @NotNull(message = "Vehicle horsepower is required")
    @Min(value = 0, message = "Horsepower must be a positive number")
    private Integer horsepower;

    public VehicleEntity() {
    }

    public String getMake() {
        return make;
    }

    public VehicleEntity setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public VehicleEntity setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public VehicleEntity setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
        return this;
    }
}
