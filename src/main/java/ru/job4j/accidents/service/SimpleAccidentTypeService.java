package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeJdbcTemplate;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {
    private final AccidentTypeJdbcTemplate accidentTypeJdbcTemplate;

    @Override
    public Collection<AccidentType> getAll() {
        return accidentTypeJdbcTemplate.getAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeJdbcTemplate.findById(id);
    }


}