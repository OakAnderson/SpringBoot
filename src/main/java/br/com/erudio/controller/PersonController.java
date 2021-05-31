package br.com.erudio.controller;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Person Endpoint", tags = "PersonEndpoint")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices services;

    @ApiOperation(value = "Find all people recorded")
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public List<PersonVO> findAll() {
        List<PersonVO> people = services.findAll();
        people.forEach(
                        person -> person.add(linkTo(methodOn(PersonController.class)
                                .findById(person.getKey()))
                                .withSelfRel()
                        )
        );

        return people;
    }

    @ApiOperation(value = "Find data of a person with the ID as a PathVariable /{id}")
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO findById(@PathVariable Long id) {
        PersonVO personVO = services.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return personVO;
    }

    @ApiOperation(value = "Record a new person into API database")
    @PostMapping(
            produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" }
    )
    public PersonVO create(@RequestBody PersonVO person) {
        PersonVO personVO = services.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());

        return personVO;
    }

    @ApiOperation(value = "Alter the data of a person recorded in the API database")
    @PutMapping(
            produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" }
    )
    public PersonVO update(@RequestBody PersonVO person) {
        PersonVO personVO = services.update(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());

        return personVO;
    }

    @ApiOperation(value = "Disable a specific person by its ID")
    @PatchMapping(value = "/{id}",
            produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO disablePerson( @PathVariable Long id) {
        PersonVO person = services.disablePerson(id);
        person.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return person;
    }

    @ApiOperation(value = "Delete a person from API database using his ID as a path variable /{id}")
    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id) {
        services.delete(id);
    }

}
