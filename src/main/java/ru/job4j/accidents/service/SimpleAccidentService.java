package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepositoryJPA;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private final AccidentRepositoryJPA accidentRepositoryJPA;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @Override
    public Accident save(Accident accident, Set<String> rIds) {
        accident.setType(accidentTypeService.findById(accident.getType().getId()).get());
        accident.setRules(ruleService.getSetRule(rIds));
        return accidentRepositoryJPA.save(accident);
    }

    @Override
    public Collection<Accident> getAll() {
        return accidentRepositoryJPA.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepositoryJPA.findById(id);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentRepositoryJPA.save(accident).getId() != 0;
    }

    @Override
    public boolean delete(int id) {
        try {
            accidentRepositoryJPA.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
