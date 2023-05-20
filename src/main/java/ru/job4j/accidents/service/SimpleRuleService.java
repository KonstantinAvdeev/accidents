package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleJdbcTemplate;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {

    private final RuleJdbcTemplate ruleJdbcTemplate;

    @Override
    public Collection<Rule> getAll() {
        return ruleJdbcTemplate.getAll();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleJdbcTemplate.findById(id);
    }

    @Override
    public Set<Rule> getSetRule(Set<String> rIds) {
        return ruleJdbcTemplate.getSetRule(rIds);
    }

}