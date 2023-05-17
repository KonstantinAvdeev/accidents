package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {

    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();
    private final AtomicInteger accidentTypeId = new AtomicInteger();

    public AccidentTypeMem() {
        add(new AccidentType(1, "Две машины"));
        add(new AccidentType(2, "Машина и человек"));
        add(new AccidentType(3, "Машина и велосипед"));
    }

    public AccidentType add(AccidentType accidentType) {
        accidentType.setId(accidentTypeId.incrementAndGet());
        accidentTypes.put(accidentType.getId(), accidentType);
        return accidentType;
    }

    public Collection<AccidentType> getAll() {
        return accidentTypes.values();
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(accidentTypes.get(id));
    }

    public boolean update(AccidentType accidentType) {
        return accidentTypes.computeIfPresent(accidentType.getId(), (id, oldAccidentType) ->
                new AccidentType(oldAccidentType.getId(), accidentType.getName())) != null;
    }

    public boolean delete(int id) {
        return accidentTypes.remove(id) != null;
    }
}