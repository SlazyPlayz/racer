package bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer;

import org.springframework.web.multipart.MultipartFile;

public class ManufacturerAddBindingModel {
    private String name;
    private String headquarters;
    private Integer foundingYear;
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
