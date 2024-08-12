package bg.softuni.exam_retake_racer.model.entity.user;

import bg.softuni.exam_retake_racer.model.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tracks")
public class TrackEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    private String name;

    @Column(name = "location")
    @NotNull(message = "Location cannot be null")
    @Size(min = 3, max = 60, message = "Location length must be between 3 and 60 characters")
    private String location;


    @Column(name = "name")
    @NotNull(message = "Track length cannot be null")
    @Min(value = 0L, message = "Track length cannot be negative")
    private Long length;

    @Column(name = "surface")
    @NotNull(message = "Surface cannot be null")
    @Size(min = 3, max = 24, message = "Surface length must be between 3 and 24 characters")
    private String surface;

    public TrackEntity() {
    }

    public String getName() {
        return name;
    }

    public TrackEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public TrackEntity setLocation(String location) {
        this.location = location;
        return this;
    }

    public Long getLength() {
        return length;
    }

    public TrackEntity setLength(Long length) {
        this.length = length;
        return this;
    }

    public String getSurface() {
        return surface;
    }

    public TrackEntity setSurface(String surface) {
        this.surface = surface;
        return this;
    }
}

