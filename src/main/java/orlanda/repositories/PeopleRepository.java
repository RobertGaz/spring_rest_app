package orlanda.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orlanda.models.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByNameOrderByAgeDesc(String name);

    List<Person> findByNameStartingWith(String s);

    List<Person> findByNameOrEmail(String n, String e);
}
