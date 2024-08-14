package bg.softuni.exam_retake_racer.model.dto.race.track;

public class TrackDTO {
    private String name;
    private String location;
    private Long length;
    private String surface;
    private String imageUrl;

    public TrackDTO() {
    }

    public String getName() {
        return name;
    }

    public TrackDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public TrackDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public Long getLength() {
        return length;
    }

    public TrackDTO setLength(Long length) {
        this.length = length;
        return this;
    }

    public String getSurface() {
        return surface;
    }

    public TrackDTO setSurface(String surface) {
        this.surface = surface;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public TrackDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
