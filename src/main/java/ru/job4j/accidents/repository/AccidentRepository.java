package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRepository {
    Accident save(Accident accident);

    Collection<Accident> getAll();

    Optional<Accident> findById(int id);

    boolean update(Accident accident);

    boolean delete(int id);

}
