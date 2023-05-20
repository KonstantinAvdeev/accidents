package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface AccidentService {
    public Accident save(Accident accident, Set<String> rIds);

    public Collection<Accident> getAll();

    public Optional<Accident> findById(int id);

    public boolean update(Accident accident);

    public boolean delete(int id);
}
