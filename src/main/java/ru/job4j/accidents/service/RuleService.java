package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface RuleService {
    public Collection<Rule> getAll();

    public Optional<Rule> findById(int id);

    public Set<Rule> getSetRule(Set<String> rIds);
}
