package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate implements AccidentTypeRepository {
    private final CrudRepository crudRepository;

    @Override
    public AccidentType add(AccidentType accidentType) {
        crudRepository.run(session -> session.persist(accidentType));
        return accidentType;
    }

    @Override
    public Collection<AccidentType> getAll() {
        return crudRepository.query("from AccidentType", AccidentType.class);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional(
                "from AccidentType where id =:fId",
                AccidentType.class,
                Map.of("fId", id));
    }

}