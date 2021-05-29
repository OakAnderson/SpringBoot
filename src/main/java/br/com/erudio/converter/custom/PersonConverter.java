package br.com.erudio.converter.custom;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVOV2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonConverter {

    public PersonVOV2 convertEntityToVO( Person person ) {
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthday(new Date());
        vo.setGender(person.getGender());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());

        return vo;
    }

    public Person convertVOToEntity( PersonVOV2 person ) {
        Person entity = new Person();

        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        return entity;
    }

}
