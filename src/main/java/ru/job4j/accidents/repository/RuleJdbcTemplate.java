package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class RuleJdbcTemplate implements RuleRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<Rule> ruleRowMapper = (resultSet, rowNum) -> {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("id"));
        rule.setName(resultSet.getString("name"));
        return rule;
    };

    @Override
    public Set<Rule> getSetRule(Set<String> rIds) {
        String r = String.join(", ", rIds);
        return new HashSet<>(jdbc.query("select * from rules where id in :Rr",
                ruleRowMapper, r));
    }

    @Override
    public Collection<Rule> getAll() {
        return jdbc.query("select * from rules", ruleRowMapper);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from rules where id = ?",
                ruleRowMapper, id));
    }

}