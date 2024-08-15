package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.race.track.TrackAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.track.TrackDTO;

import java.util.Set;

public interface TrackService {

    void addTrack(TrackAddBindingModel trackAddBindingModel);

    TrackDTO getTrackBySearchName(String name);

    Set<TrackDTO> getAllTracks();

    Set<String> getPhotoIds();
}
