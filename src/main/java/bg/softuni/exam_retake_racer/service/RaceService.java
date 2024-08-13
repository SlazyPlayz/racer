package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.RaceDTO;

public interface RaceService {

    void addRace(RaceAddBindingModel raceAddBindingModel);

    RaceDTO getRaceByName(String name);

    void participateInRace(String raceName);
}
