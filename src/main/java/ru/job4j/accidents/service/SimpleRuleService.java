package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepositoryJPA;

import java.util.*;

@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {

    private final RuleRepositoryJPA ruleRepositoryJPA;

    @Override
    public Collection<Rule> getAll() {
        return ruleRepositoryJPA.findAll();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleRepositoryJPA.findById(id);
    }

    @Override
    public Set<Rule> getSetRule(Set<String> rIds) {
        return ruleRepositoryJPA.findRulesByIdIn(rIds);
    }

}