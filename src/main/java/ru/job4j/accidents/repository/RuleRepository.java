package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface RuleRepository {

    Set<Rule> getSetRule(Set<String> rIds);

    Collection<Rule> getAll();

    Optional<Rule> findById(int id);

}