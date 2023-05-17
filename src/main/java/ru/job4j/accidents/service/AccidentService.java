package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;
    private final AccidentTypeService accidentTypeService;

    public Accident save(Accident accident) {
        return accidentMem.save(accident);
    }

    public Collection<Accident> getAll() {
        return accidentMem.getAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public boolean update(Accident accident) {
        return accidentMem.update(accident);
    }

    public Collection<AccidentType> getTypes() {
        return accidentTypeService.getAll();
    }

    public AccidentType getType(int id) {
        return accidentTypeService.findById(id).get();
    }

}
