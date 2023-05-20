package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeRepository {
    AccidentType add(AccidentType accidentType);

    Collection<AccidentType> getAll();

    Optional<AccidentType> findById(int id);
}
