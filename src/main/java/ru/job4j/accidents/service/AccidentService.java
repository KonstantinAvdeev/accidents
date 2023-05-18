package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public Accident save(Accident accident, int id, Set<Rule> rIds) {
        accident.setType(accidentTypeService.findById(id).get());
        accident.setRules(ruleService.getSetRule(rIds));
        return accidentMem.save(accident);
    }

    public Collection<Accident> getAll() {
        return accidentMem.getAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public boolean update(Accident accident) {
        return accidentMem.update(accident);
    }

    public boolean delete(int id) {
        return accidentMem.delete(id);
    }

}
