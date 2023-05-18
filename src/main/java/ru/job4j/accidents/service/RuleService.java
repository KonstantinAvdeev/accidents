package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleMem;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {
    private final RuleMem ruleMem;

    public Rule add(Rule rule) {
        return ruleMem.add(rule);
    }

    public Collection<Rule> getAll() {
        return ruleMem.getAll();
    }

    public Optional<Rule> findById(int id) {
        return ruleMem.findById(id);
    }

    public Set<Rule> getSetRule(Set<Rule> rIds) {
        return ruleMem.getSetRule(rIds);
    }

    public boolean update(Rule rule) {
        return ruleMem.update(rule);
    }

    public boolean delete(int id) {
        return ruleMem.delete(id);
    }

}