package br.com.erudio.restwithspringbootudemy.repository;

import br.com.erudio.restwithspringbootudemy.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
