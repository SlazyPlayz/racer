package bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ManufacturerAddBindingModel {

    @NotNull(message = "Manufacturer name is required")
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    private String name;

    @NotNull(message = "Organizer headquarters cannot be null")
    @Size(min = 3, max = 60, message = "Organizer headquarters length must be between 3 and 60 characters")
    private String headquarters;

    @NotNull(message = "Organizer founding year cannot be null")
    @Min(1000)
    @Max(value = 2024, message = "Organizer founding year cannot be in the future")
    private Integer foundingYear;

    @NotNull(message = "Organizer image cannot be null")
    private MultipartFile image;

    public ManufacturerAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ManufacturerAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public ManufacturerAddBindingModel setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
        return this;
    }

    public Integer getFoundingYear() {
        return foundingYear;
    }

    public ManufacturerAddBindingModel setFoundingYear(Integer foundingYear) {
        this.foundingYear = foundingYear;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public ManufacturerAddBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
