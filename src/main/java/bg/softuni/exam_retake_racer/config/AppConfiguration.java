package bg.softuni.exam_retake_racer.config;

import bg.softuni.exam_retake_racer.service.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class AppConfiguration {

    private final CloudinaryService cloudinaryService;
    private final UserService userService;
    private final TrackService trackService;
    private final ManufacturerService manufacturerService;
    private final OrganizerService organizerService;

    public AppConfiguration(CloudinaryService cloudinaryService, UserService userService, TrackService trackService, ManufacturerService manufacturerService, OrganizerService organizerService) {
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
        this.trackService = trackService;
        this.manufacturerService = manufacturerService;
        this.organizerService = organizerService;
    }

    @Scheduled(cron = "0 0 4 * * *")
    public void deleteUnusedFiles() {
        Set<String> photoIds = new HashSet<>();
        photoIds.addAll(userService.getPhotoIds());
        photoIds.addAll(trackService.getPhotoIds());
        photoIds.addAll(manufacturerService.getPhotoIds());
        photoIds.addAll(organizerService.getPhotoIds());
        cloudinaryService.deleteFiles(photoIds);
    }
}
