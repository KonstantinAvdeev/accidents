package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import ru.job4j.accidents.model.Accident;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger accidentId = new AtomicInteger(1);

    public AccidentMem() {
        save(new Accident(0, "Неправильная парковка", "Припарковался на газоне",
                "ул.Пушкина, д.5"));
        save(new Accident(0, "Превышение скорости",
                "Превысил скорость по городу, навскидку на 30 км/ч", "ул.Ленина, напротив д.11"));
        save(new Accident(0, "Неправильная парковка",
                "Перегородил проезд, создал помеху экстренным службам", "ул.Ватутина, д.26"));
    }

    public Accident save(Accident accident) {
        accident.setId(accidentId.getAndIncrement());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

}