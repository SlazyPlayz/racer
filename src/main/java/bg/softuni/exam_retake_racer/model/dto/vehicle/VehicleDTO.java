package bg.softuni.exam_retake_racer.model.dto.vehicle;

public class VehicleDTO {

    private String make;
    private String model;
    private Integer year;
    private Integer horsepower;

    public VehicleDTO() {
    }

    public String getMake() {
        return make;
    }

    public VehicleDTO setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public VehicleDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public VehicleDTO setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
        return this;
    }
}
