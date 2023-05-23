package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepositoryJPA;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {
    private final AccidentTypeRepositoryJPA accidentTypeRepositoryJPA;

    @Override
    public Collection<AccidentType> getAll() {
        return accidentTypeRepositoryJPA.findAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepositoryJPA.findById(id);
    }


}