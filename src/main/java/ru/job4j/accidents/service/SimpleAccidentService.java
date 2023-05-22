package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleAccidentService {
    private final AccidentRepository accidentJdbcTemplate;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public Accident save(Accident accident, Set<String> rIds) {
        accident.setType(accidentTypeService.findById(accident.getType().getId()).get());
        accident.setRules(ruleService.getSetRule(rIds));
        return accidentJdbcTemplate.save(accident);
    }

    public Collection<Accident> getAll() {
        return accidentJdbcTemplate.getAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentJdbcTemplate.findById(id);
    }

    public boolean update(Accident accident) {
        return accidentJdbcTemplate.update(accident);
    }

    public boolean delete(int id) {
        return accidentJdbcTemplate.delete(id);
    }

}
