package bg.softuni.exam_retake_racer.model.entity.race;

import bg.softuni.exam_retake_racer.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "organizers")
public class OrganizerEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    @NotNull(message = "Organizer name cannot be null")
    @Size(min = 3, max = 32, message = "Name length must be between 3 and 32 characters")
    private String name;

    @Column(name = "headquarters")
    @NotNull(message = "Organizer headquarters cannot be null")
    @Size(min = 3, max = 60, message = "Organizer headquarters length must be between 3 and 60 characters")
    private String headquarters;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "founding_year")
    @NotNull(message = "Organizer founding year cannot be null")
    @Min(1000)
    @Max(value = 2024, message = "Organizer founding year cannot be in the future")
    private Integer foundingYear;

    @Column(name = "search_name")
    private String searchName;

    public OrganizerEntity() {
    }

    public String getName() {
        return name;
    }

    public OrganizerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public OrganizerEntity setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
        return this;
    }

    public Integer getFoundingYear() {
        return foundingYear;
    }

    public OrganizerEntity setFoundingYear(Integer foundingYear) {
        this.foundingYear = foundingYear;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrganizerEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getSearchName() {
        return searchName;
    }

    public OrganizerEntity setSearchName(String searchName) {
        this.searchName = searchName;
        return this;
    }
}
