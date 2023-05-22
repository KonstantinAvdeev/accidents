package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {

    private final RuleRepository ruleHibernate;

    @Override
    public Collection<Rule> getAll() {
        return ruleHibernate.getAll();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleHibernate.findById(id);
    }

    @Override
    public Set<Rule> getSetRule(Set<String> rIds) {
        return ruleHibernate.getSetRule(rIds);
    }

}