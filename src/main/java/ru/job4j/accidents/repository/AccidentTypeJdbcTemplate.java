package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class AccidentTypeJdbcTemplate implements AccidentTypeRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<AccidentType> accidentTypeRowMapper = (resultSet, rowNum) -> {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(resultSet.getInt("id"));
        accidentType.setName(resultSet.getString("name"));
        return accidentType;
    };

    @Override
    public AccidentType add(AccidentType accidentType) {
        jdbc.update("insert into accident_types (name) values (?)",
                accidentType.getName());
        return accidentType;
    }

    @Override
    public Collection<AccidentType> getAll() {
        return jdbc.query("select * from accident_types", accidentTypeRowMapper);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from accident_types where id = ?",
                accidentTypeRowMapper, id));
    }
}
