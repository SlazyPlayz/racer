package bg.softuni.exam_retake_racer.model.dto.race.organizer;

import org.springframework.web.multipart.MultipartFile;

public class OrganizerAddBindingModel {

    private String name;

    private String headquarters;

    private MultipartFile image;

    private Integer foundingYear;

    private String identificationNumber;

    public OrganizerAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public OrganizerAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public OrganizerAddBindingModel setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
        return this;
    }

    public Integer getFoundingYear() {
        return foundingYear;
    }

    public OrganizerAddBindingModel setFoundingYear(Integer foundingYear) {
        this.foundingYear = foundingYear;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public OrganizerAddBindingModel setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public OrganizerAddBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
