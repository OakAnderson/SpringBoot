package br.com.erudio.restwithspringbootudemy.services;

import br.com.erudio.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.erudio.restwithspringbootudemy.model.Person;
import br.com.erudio.restwithspringbootudemy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById( Long id ) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public Person create ( Person person ) {
        return repository.save(person);
    }

    public Person update ( Person person ) {
        Person entity = repository.getById(person.getId());

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete ( Long id ) {
        Person person = repository.getById(id);

        repository.delete(person);
    }



}
