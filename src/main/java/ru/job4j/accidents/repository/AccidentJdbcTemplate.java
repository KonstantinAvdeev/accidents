package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRepository {
    private final JdbcTemplate jdbc;
    private final AtomicInteger accidentId = new AtomicInteger();
    private final RowMapper<Accident> accidentRowMapper = (resultSet, rowNum) -> {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("id"));
        accident.setName(resultSet.getString("name"));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        AccidentType accidentType = new AccidentType();
        accidentType.setName(resultSet.getString("typeName"));
        accident.setType(accidentType);
        int ruleId = resultSet.getInt("rule_id");
        if (ruleId != 0) {
            Rule rule = new Rule();
            rule.setId(ruleId);
            rule.setName(resultSet.getString("rule_name"));
            accident.getRules().add(rule);
        }
        return accident;
    };

    @Override
    public Accident save(Accident accident) {
        jdbc.update("insert into accidents (name, text, address, accident_type_id) "
                        + "values (?, ?, ?, ?)",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId());
        saveAccidentsRules(accident);
        return accident;
    }

    @Override
    public Collection<Accident> getAll() {
        return jdbc.query("select ac.id, ac.name, ac.text, ac.address, atypes.name as typeName, "
                        + "ar.name as rule_name from accidents ac "
                        + "join accident_types atypes on ac.accident_type_id = atypes.id "
                        + "join accidents_rules ar on ar.accident_id = ac.id "
                        + "join rules AS r ON ar.rule_id = r.id ",
                accidentRowMapper);
    }

    @Override
    public Optional<Accident> findById(int accidentId) {
        return Optional.ofNullable(jdbc.queryForObject("select ac.id, ac.name, ac.text, "
                        + "ac.address, atypes.name as typeName, "
                        + "ar.name as rule_name from accidents ac "
                        + "join accident_types atypes on ac.accident_type_id = atypes.id "
                        + "join accidents_rules ar on ar.accident_id = ac.id "
                        + "join rules r on ar.rule_id = r.id where ac.id = ?",
                accidentRowMapper, accidentId));
    }

    @Override
    public boolean update(Accident accident) {
        int upd = jdbc.update("update accidents set name = ?, text = ?, address = ?, "
                        + "accident_type_id = ? where id  = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        if (upd > 0) {
            deleteAccidentsRules(accident.getId());
            saveAccidentsRules(accident);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(int accidentId) {
        int del = jdbc.update("delete from accidents where id = ?", accidentId);
        if (del > 0) {
            deleteAccidentsRules(accidentId);
            return true;
        } else {
            return false;
        }
    }

    private void saveAccidentsRules(Accident accident) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accidents_rules(accident_id, rule_id) values (?, ?)",
                    accident.getId(), rule.getId());
        }
    }

    private void deleteAccidentsRules(int accidentId) {
        jdbc.update("delete from accidents_rules where accident_id = ?", accidentId);
    }
}