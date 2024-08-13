package bg.softuni.exam_retake_racer.model.dto;

public class OrganizerDTO {

    private String name;

    private String headquarters;

    private String imageUrl;

    private Integer foundingYear;

    private String identificationNumber;

    public OrganizerDTO() {
    }

    public String getName() {
        return name;
    }

    public OrganizerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public OrganizerDTO setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
        return this;
    }

    public Integer getFoundingYear() {
        return foundingYear;
    }

    public OrganizerDTO setFoundingYear(Integer foundingYear) {
        this.foundingYear = foundingYear;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public OrganizerDTO setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrganizerDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
