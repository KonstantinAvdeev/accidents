package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem {

    private final Map<Integer, Rule> ruleMap = new ConcurrentHashMap<>();
    private final AtomicInteger ruleId = new AtomicInteger();

    public RuleMem() {
        add(new Rule(0, "Статья 1: Превышение скорости"));
        add(new Rule(0, "Статья 2: Проезд на красный сигнал светофора"));
        add(new Rule(0, "Статья 3: Не пропуск пешехода"));
    }

    public Rule add(Rule rule) {
        rule.setId(ruleId.incrementAndGet());
        ruleMap.put(rule.getId(), rule);
        return rule;
    }

    public Collection<Rule> getAll() {
        return ruleMap.values();
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(ruleMap.get(id));
    }

    public Set<Rule> getSetRule(Set<String> rIds) {
        Set<Rule> rules = new HashSet<>();
        for (String id : rIds) {
            rules.add(findById(Integer.parseInt(id)).get());
        }
        return rules;
    }

    public boolean update(Rule rule) {
        return ruleMap.computeIfPresent(rule.getId(), (id, oldRule) ->
                new Rule(oldRule.getId(), rule.getName())) != null;
    }

    public boolean delete(int id) {
        return ruleMap.remove(id) != null;
    }

}
