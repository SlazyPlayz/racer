package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.TrackAlreadyExistsError;
import bg.softuni.exam_retake_racer.exceptions.TrackNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.race.track.TrackAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.track.TrackDTO;
import bg.softuni.exam_retake_racer.model.entity.race.TrackEntity;
import bg.softuni.exam_retake_racer.repository.TrackRepository;
import bg.softuni.exam_retake_racer.service.TrackService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryServiceImpl cloudinaryServiceImpl;

    public TrackServiceImpl(TrackRepository trackRepository, ModelMapper modelMapper, CloudinaryServiceImpl cloudinaryServiceImpl) {
        this.trackRepository = trackRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryServiceImpl = cloudinaryServiceImpl;
    }

    @Override
    public void addTrack(TrackAddBindingModel trackAddBindingModel) {
        trackRepository.findTrackBySearchName(toSearchName(trackAddBindingModel.getName()))
                .ifPresent(trackEntity -> {
                    throw new TrackAlreadyExistsError(trackAddBindingModel.getName());
                });

        String imageUrl = cloudinaryServiceImpl
                .uploadFile(trackAddBindingModel.getImage(), "/tracks/" + trackAddBindingModel.getName());

        TrackEntity trackEntity = modelMapper.map(trackAddBindingModel, TrackEntity.class);

        trackEntity.setSearchName(toSearchName(trackAddBindingModel.getName()));

        trackEntity.setImageUrl(imageUrl);

        trackRepository.save(trackEntity);
    }

    @Override
    public TrackDTO getTrackBySearchName(String name) {
        return trackRepository.findTrackBySearchName(toSearchName(name))
                .map(trackEntity -> modelMapper.map(trackEntity, TrackDTO.class))
                .orElseThrow(() -> new TrackNotFoundException(name));
    }

    @Override
    public Set<TrackDTO> getAllTracks() {
        return trackRepository.findAll().stream().map(trackEntity -> modelMapper.map(trackEntity, TrackDTO.class)).collect(Collectors.toSet());
    }

    private String toSearchName(String name) {
        return name.replace(" ", "-").toLowerCase();
    }
}
