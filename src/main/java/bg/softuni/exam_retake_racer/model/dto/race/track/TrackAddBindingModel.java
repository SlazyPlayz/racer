package bg.softuni.exam_retake_racer.model.dto.race.track;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class TrackAddBindingModel {

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    private String name;

    @NotNull(message = "Location cannot be null")
    @Size(min = 3, max = 60, message = "Location length must be between 3 and 60 characters")
    private String location;

    @NotNull(message = "Track length cannot be null")
    @Min(value = 0L, message = "Track length cannot be negative")
    private Long length;

    @NotNull(message = "Surface cannot be null")
    @Size(min = 3, max = 24, message = "Surface length must be between 3 and 24 characters")
    private String surface;

    @NotNull
    private MultipartFile image;

    public TrackAddBindingModel() {
    }

    public @NotNull(message = "Name cannot be null") @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters") String getName() {
        return name;
    }

    public TrackAddBindingModel setName(@NotNull(message = "Name cannot be null") @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters") String name) {
        this.name = name;
        return this;
    }

    public @NotNull(message = "Location cannot be null") @Size(min = 3, max = 60, message = "Location length must be between 3 and 60 characters") String getLocation() {
        return location;
    }

    public TrackAddBindingModel setLocation(@NotNull(message = "Location cannot be null") @Size(min = 3, max = 60, message = "Location length must be between 3 and 60 characters") String location) {
        this.location = location;
        return this;
    }

    public @NotNull(message = "Track length cannot be null") @Min(value = 0L, message = "Track length cannot be negative") Long getLength() {
        return length;
    }

    public TrackAddBindingModel setLength(@NotNull(message = "Track length cannot be null") @Min(value = 0L, message = "Track length cannot be negative") Long length) {
        this.length = length;
        return this;
    }

    public @NotNull(message = "Surface cannot be null") @Size(min = 3, max = 24, message = "Surface length must be between 3 and 24 characters") String getSurface() {
        return surface;
    }

    public TrackAddBindingModel setSurface(@NotNull(message = "Surface cannot be null") @Size(min = 3, max = 24, message = "Surface length must be between 3 and 24 characters") String surface) {
        this.surface = surface;
        return this;
    }

    public @NotNull MultipartFile getImage() {
        return image;
    }

    public TrackAddBindingModel setImage(@NotNull MultipartFile image) {
        this.image = image;
        return this;
    }
}
