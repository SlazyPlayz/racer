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
}
