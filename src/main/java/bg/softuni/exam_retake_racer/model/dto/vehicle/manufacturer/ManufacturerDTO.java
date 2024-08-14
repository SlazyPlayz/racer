package bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer;

public class ManufacturerDTO {
    private String name;
    private String headquarters;
    private Integer foundingYear;
    private String imageUrl;

    public ManufacturerDTO() {
    }

    public String getName() {
        return name;
    }

    public ManufacturerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public ManufacturerDTO setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
        return this;
    }

    public Integer getFoundingYear() {
        return foundingYear;
    }

    public ManufacturerDTO setFoundingYear(Integer foundingYear) {
        this.foundingYear = foundingYear;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ManufacturerDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
