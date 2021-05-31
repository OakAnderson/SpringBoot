package br.com.erudio.controller;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Person Endpoint", tags = "PersonEndpoint")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices services;

    @Autowired
    private PagedResourcesAssembler<PersonVO> assembler;

    @ApiOperation(value = "Find all people recorded")
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "15") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> people = services.findAll(pageable);
        people.forEach(
                        person -> person.add(linkTo(methodOn(PersonController.class)
                                .findById(person.getKey()))
                                .withSelfRel()
                        )
        );

        PagedModel<?> pagedModel = assembler.toModel(people);

        return ResponseEntity.ok(pagedModel);
    }

    @ApiOperation(value = "Find all people recorded")
    @GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findPersonByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "15") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> people = services.findPersonByName(firstName, pageable);
        people.forEach(
                person -> person.add(linkTo(methodOn(PersonController.class)
                        .findById(person.getKey()))
                        .withSelfRel()
                )
        );
        PagedModel<?> pagedModel = assembler.toModel(people);

        return ResponseEntity.ok(pagedModel);
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
