package ru.job4j.accidents.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;


public class AccidentMem implements AccidentRepository {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger accidentId = new AtomicInteger();

    public AccidentMem() {
        save(new Accident(0, "ДТП с пострадавшими", "ДТП с травмами средней тяжести, "
                + "без жертв", "ул.Пушкина, д.5",
                new AccidentType(), Set.of(new Rule(0, "Rule1"))));
        save(new Accident(0, "Сбили пешехода", "ДТП с участием автомобиля и пешехода, "
                + "водитель сбил пешехода в 15 метрах от пешеходного перехода",
                "ул.Ленина, напротив д.11", new AccidentType(),
                Set.of(new Rule(0, "Rule2"))));
        save(new Accident(0, "Сбили велосипедиста", "ДТП с участием автомобиля и "
                + "велосипедиста, велосипедист не спешился на пешеходном переходе, травмировал "
                + "колено и локоть, требуется вызов скорой помощи", "ул.Ватутина, д.26",
                new AccidentType(), Set.of(new Rule(0, "Rule3"))));
    }

    @Override
    public Accident save(Accident accident) {
        accident.setId(accidentId.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Collection<Accident> getAll() {
        return accidents.values();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (id, oldAccident) ->
                new Accident(oldAccident.getId(), accident.getName(), accident.getText(),
                        accident.getAddress(), accident.getType(), accident.getRules())) != null;
    }

    @Override
    public boolean delete(int id) {
        return accidents.remove(id) != null;
    }

}