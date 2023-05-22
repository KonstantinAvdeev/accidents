package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;

@Repository
@AllArgsConstructor
public class RuleHibernate implements RuleRepository {
    private final CrudRepository crudRepository;

    @Override
    public Set<Rule> getSetRule(Set<String> rIds) {
        String r = String.join(", ", rIds);
        List<Rule> list = crudRepository.query("from Rule where id in :Rr", Rule.class,
                Map.of("Rr", r));
        return new HashSet<>(list);
    }

    @Override
    public Collection<Rule> getAll() {
        return crudRepository.query("from Rule", Rule.class);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return crudRepository.optional(
                "from Rule where id =:fId",
                Rule.class,
                Map.of("fId", id));
    }

}