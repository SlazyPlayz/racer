package bg.softuni.exam_retake_racer.model.dto.race;

public class DisplayRaceDTO {

    private String name;
    private String track;
    private String imageUrl;

    public DisplayRaceDTO() {
    }

    public String getName() {
        return name;
    }

    public DisplayRaceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getTrack() {
        return track;
    }

    public DisplayRaceDTO setTrack(String track) {
        this.track = track;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DisplayRaceDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
