package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;


@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger accidentId = new AtomicInteger();
    private AccidentTypeMem accidentTypeMem = new AccidentTypeMem();

    public AccidentMem() {
        save(new Accident(0, "ДТП с пострадавшими", "ДТП с травмами средней тяжести, "
                + "без жертв", "ул.Пушкина, д.5",
                accidentTypeMem.findById(1).get()));
        save(new Accident(0, "Сбили пешехода", "ДТП с участием автомобиля и пешехода, "
                + "водитель сбил пешехода в 15 метрах от пешеходного перехода",
                "ул.Ленина, напротив д.11", accidentTypeMem.findById(2).get()));
        save(new Accident(0, "Сбили велосипедиста", "ДТП с участием автомобиля и "
                + "велосипедиста, велосипедист не спешился на пешеходном переходе, травмировал "
                + "колено и локоть, требуется вызов скорой помощи", "ул.Ватутина, д.26",
                accidentTypeMem.findById(3).get()));
    }

    public Accident save(Accident accident) {
        accident.setId(accidentId.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (id, oldAccident) ->
                new Accident(oldAccident.getId(), accident.getName(), accident.getText(),
                        accident.getAddress(), accident.getType())) != null;
    }

    public Collection<AccidentType> getTypes() {
        return accidentTypeMem.getAll();
    }

    public AccidentType getType(int id) {
        return accidentTypeMem.findById(id).get();
    }

}