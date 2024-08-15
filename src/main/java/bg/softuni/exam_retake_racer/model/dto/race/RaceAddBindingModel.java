package bg.softuni.exam_retake_racer.model.dto.race;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RaceAddBindingModel {

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 60, message = "Name length must be between 3 and 60 characters")
    private String name;

    @NotNull(message = "Race type is required")
    @Size(min = 3, max = 20, message = "Track length must be between 3 and 20 characters")
    private String type;

    @NotNull(message = "Track cannot be null")
    private String track;

    @NotNull(message = "Race description is required")
    @Size(min = 3, max = 120, message = "Track description must be between 3 and 120 characters")
    private String description;

    @NotNull(message = "Date must not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message = "Race prize is required")
    @Min(value = 0, message = "Prize cannot be a negative number")
    private Double prize;

    @NotNull(message = "Organizer cannot be null")
    private String organizer;

    public RaceAddBindingModel() {
    }

    public @NotNull(message = "Name is required") @Size(min = 3, max = 60, message = "Name length must be between 3 and 60 characters") String getName() {
        return name;
    }

    public RaceAddBindingModel setName(@NotNull(message = "Name is required") @Size(min = 3, max = 60, message = "Name length must be between 3 and 60 characters") String name) {
        this.name = name;
        return this;
    }

    public @NotNull(message = "Race type is required") @Size(min = 3, max = 20, message = "Track length must be between 3 and 20 characters") String getType() {
        return type;
    }

    public RaceAddBindingModel setType(@NotNull(message = "Race type is required") @Size(min = 3, max = 20, message = "Track length must be between 3 and 20 characters") String type) {
        this.type = type;
        return this;
    }

    public @NotNull(message = "Track cannot be null") String getTrack() {
        return track;
    }

    public RaceAddBindingModel setTrack(@NotNull(message = "Track cannot be null") String track) {
        this.track = track;
        return this;
    }

    public @NotNull(message = "Race description is required") @Size(min = 3, max = 120, message = "Track description must be between 3 and 120 characters") String getDescription() {
        return description;
    }

    public RaceAddBindingModel setDescription(@NotNull(message = "Race description is required") @Size(min = 3, max = 120, message = "Track description must be between 3 and 120 characters") String description) {
        this.description = description;
        return this;
    }

    public @NotNull(message = "Date must not be null") Date getDate() {
        return date;
    }

    public RaceAddBindingModel setDate(@NotNull(message = "Date must not be null") Date date) {
        this.date = date;
        return this;
    }

    public @NotNull(message = "Race prize is required") @Min(value = 0, message = "Prize cannot be a negative number") Double getPrize() {
        return prize;
    }

    public RaceAddBindingModel setPrize(@NotNull(message = "Race prize is required") @Min(value = 0, message = "Prize cannot be a negative number") Double prize) {
        this.prize = prize;
        return this;
    }

    public @NotNull(message = "Organizer cannot be null") String getOrganizer() {
        return organizer;
    }

    public RaceAddBindingModel setOrganizer(@NotNull(message = "Organizer cannot be null") String organizer) {
        this.organizer = organizer;
        return this;
    }
}
