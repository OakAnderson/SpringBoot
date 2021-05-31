package br.com.erudio.services;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll(Pageable pageable) {
        var entities = repository.findAll(pageable).getContent();
        return DozerConverter.parseListObjects(entities, PersonVO.class);
    }

    public PersonVO findById(Long id ) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(person, PersonVO.class);
    }

    public PersonVO create (PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);

        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update (PersonVO person) {
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);

    }

    @Transactional
    public PersonVO disablePerson(Long id) {
        repository.disablePerson(id);
        return DozerConverter.parseObject(
                repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")),
                PersonVO.class
        );
    }

    public void delete ( Long id ) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }



}
