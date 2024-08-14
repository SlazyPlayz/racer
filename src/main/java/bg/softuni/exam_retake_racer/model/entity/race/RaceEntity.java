package bg.softuni.exam_retake_racer.model.entity.race;

import bg.softuni.exam_retake_racer.model.entity.BaseEntity;
import bg.softuni.exam_retake_racer.model.entity.user.ParticipantEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "races")
public class RaceEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 60, message = "Name length must be between 3 and 60 characters")
    private String name;

    @Column(name = "search_name")
    private String searchName;

    @Column(name = "type")
    @NotNull(message = "Race type is required")
    @Size(min = 3, max = 20, message = "Track length must be between 3 and 20 characters")
    private String type;

    @ManyToOne
    @JoinColumn(name = "track_uuid")
    private TrackEntity track;

    @Column(name = "description")
    @NotNull(message = "Race description is required")
    @Size(min = 3, max = 120, message = "Track description must be between 3 and 120 characters")
    private String description;

    @Column(name = "date")
    @NotNull(message = "Date must not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "prize")
    @NotNull(message = "Race prize is required")
    @Min(value = 0, message = "Prize cannot be a negative number")
    private Double prize;

    @Column(name = "image_url")
    @NotNull
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "organizer_uuid")
    private OrganizerEntity organizer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ParticipantEntity> participants;

    public RaceEntity() {
    }

    public String getName() {
        return name;
    }

    public RaceEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public RaceEntity setType(String type) {
        this.type = type;
        return this;
    }

    public TrackEntity getTrack() {
        return track;
    }

    public RaceEntity setTrack(TrackEntity track) {
        this.track = track;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RaceEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrize() {
        return prize;
    }

    public RaceEntity setPrize(Double prize) {
        this.prize = prize;
        return this;
    }

    public OrganizerEntity getOrganizer() {
        return organizer;
    }

    public RaceEntity setOrganizer(OrganizerEntity organizer) {
        this.organizer = organizer;
        return this;
    }

    public Set<ParticipantEntity> getParticipants() {
        return participants;
    }

    public RaceEntity setParticipants(Set<ParticipantEntity> participants) {
        this.participants = participants;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public RaceEntity setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getSearchName() {
        return searchName;
    }

    public RaceEntity setSearchName(String searchName) {
        this.searchName = searchName;
        return this;
    }

    public @NotNull String getImageUrl() {
        return imageUrl;
    }

    public RaceEntity setImageUrl(@NotNull String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
