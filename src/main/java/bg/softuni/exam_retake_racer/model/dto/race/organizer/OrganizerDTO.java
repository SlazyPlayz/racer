package bg.softuni.exam_retake_racer.model.dto.race.organizer;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrganizerDTO {

    @NotNull(message = "Organizer name cannot be null")
    @Size(min = 3, max = 32, message = "Name length must be between 3 and 32 characters")
    private String name;

    @NotNull(message = "Organizer headquarters cannot be null")
    @Size(min = 3, max = 60, message = "Organizer headquarters length must be between 3 and 60 characters")
    private String headquarters;

    private String imageUrl;

    @NotNull(message = "Organizer founding year cannot be null")
    @Min(1000)
    @Max(value = 2024, message = "Organizer founding year cannot be in the future")
    private Integer foundingYear;

    public OrganizerDTO() {
    }

    public @NotNull(message = "Organizer name cannot be null") @Size(min = 3, max = 32, message = "Name length must be between 3 and 32 characters") String getName() {
        return name;
    }

    public OrganizerDTO setName(@NotNull(message = "Organizer name cannot be null") @Size(min = 3, max = 32, message = "Name length must be between 3 and 32 characters") String name) {
        this.name = name;
        return this;
    }

    public @NotNull(message = "Organizer headquarters cannot be null") @Size(min = 3, max = 60, message = "Organizer headquarters length must be between 3 and 60 characters") String getHeadquarters() {
        return headquarters;
    }

    public OrganizerDTO setHeadquarters(@NotNull(message = "Organizer headquarters cannot be null") @Size(min = 3, max = 60, message = "Organizer headquarters length must be between 3 and 60 characters") String headquarters) {
        this.headquarters = headquarters;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrganizerDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public @NotNull(message = "Organizer founding year cannot be null") @Min(1000) @Max(value = 2024, message = "Organizer founding year cannot be in the future") Integer getFoundingYear() {
        return foundingYear;
    }

    public OrganizerDTO setFoundingYear(@NotNull(message = "Organizer founding year cannot be null") @Min(1000) @Max(value = 2024, message = "Organizer founding year cannot be in the future") Integer foundingYear) {
        this.foundingYear = foundingYear;
        return this;
    }
}
