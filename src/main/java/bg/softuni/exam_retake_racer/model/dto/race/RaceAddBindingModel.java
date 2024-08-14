package bg.softuni.exam_retake_racer.model.dto.race;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RaceAddBindingModel {
    private String name;
    private String type;
    private String track;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Double prize;
    private String organizer;
    private Boolean willParticipate;

    public RaceAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public RaceAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public RaceAddBindingModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getTrack() {
        return track;
    }

    public RaceAddBindingModel setTrack(String track) {
        this.track = track;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RaceAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public RaceAddBindingModel setDate(Date date) {
        this.date = date;
        return this;
    }

    public Double getPrize() {
        return prize;
    }

    public RaceAddBindingModel setPrize(Double prize) {
        this.prize = prize;
        return this;
    }

    public String getOrganizer() {
        return organizer;
    }

    public RaceAddBindingModel setOrganizer(String organizer) {
        this.organizer = organizer;
        return this;
    }

    public Boolean getWillParticipate() {
        return willParticipate;
    }

    public RaceAddBindingModel setWillParticipate(Boolean willParticipate) {
        this.willParticipate = willParticipate;
        return this;
    }
}
