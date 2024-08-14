package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.RaceDTO;
import bg.softuni.exam_retake_racer.model.dto.vehicle.VehicleDTO;

import java.util.Set;

public interface RaceService {

    void addRace(RaceAddBindingModel raceAddBindingModel);

    RaceDTO getRaceByName(String name);

    void participateInRace(String raceName, VehicleDTO vehicleDTO);

    Set<RaceDTO> getAllRaces();
}
