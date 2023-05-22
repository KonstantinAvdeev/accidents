package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentRepository {
    private final CrudRepository crudRepository;

    public Accident save(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return accident;
    }

    public Collection<Accident> getAll() {
        return crudRepository.query(
                "SELECT DISTINCT ac FROM Accident ac "
                        + "JOIN FETCH ac.type "
                        + "JOIN FETCH ac.rules",
                Accident.class);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return crudRepository.optional(
                "from Accident AS ac "
                        + "JOIN FETCH ac.type "
                        + "JOIN FETCH ac.rules "
                        + "WHERE ac.id =:fId",
                Accident.class,
                Map.of("fId", id));
    }

    @Override
    public boolean update(Accident accident) {
        try {
            crudRepository.run(session -> session.merge(accident));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            crudRepository.run("delete from Accident where id =:fId",
                    Map.of("fId", id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}