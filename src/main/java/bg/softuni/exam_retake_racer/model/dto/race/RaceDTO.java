package bg.softuni.exam_retake_racer.model.dto.race;

import java.util.Date;
import java.util.Set;

public class RaceDTO {
    private String name;
    private String type;
    private String track;
    private String description;
    private Date date;
    private Double prize;
    private String organizer;
    private Boolean hasPassed;
    private String imageUrl;
    private Set<String> participants;

    public RaceDTO() {
    }


    public String getName() {
        return name;
    }

    public RaceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public RaceDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getTrack() {
        return track;
    }

    public RaceDTO setTrack(String track) {
        this.track = track;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RaceDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public RaceDTO setDate(Date date) {
        this.date = date;
        return this;
    }

    public Double getPrize() {
        return prize;
    }

    public RaceDTO setPrize(Double prize) {
        this.prize = prize;
        return this;
    }

    public String getOrganizer() {
        return organizer;
    }

    public RaceDTO setOrganizer(String organizer) {
        this.organizer = organizer;
        return this;
    }

    public Boolean getHasPassed() {
        return hasPassed;
    }

    public RaceDTO setHasPassed(Boolean hasPassed) {
        this.hasPassed = hasPassed;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public RaceDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Set<String> getParticipants() {
        return participants;
    }

    public RaceDTO setParticipants(Set<String> participants) {
        this.participants = participants;
        return this;
    }
}
